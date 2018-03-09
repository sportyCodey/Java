import graphicslib3D.*;
import graphicslib3D.GLSLUtils.*;

import java.io.File;
import java.nio.*;
import javax.swing.*;

import static com.jogamp.opengl.GL.GL_ARRAY_BUFFER;
import static com.jogamp.opengl.GL.GL_FLOAT;
import static com.jogamp.opengl.GL.GL_STATIC_DRAW;
import static com.jogamp.opengl.GL.GL_TEXTURE0;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GLContext;

public class Exercise_5_3 extends JFrame implements GLEventListener
{	private GLCanvas myCanvas;
	private int rendering_program;
	private int vao[] = new int[1];
	private int vbo[] = new int[4];
	private float cameraX, cameraY, cameraZ;
	private float cubeLocX, cubeLocY, cubeLocZ;
	private float pyrLocX, pyrLocY, pyrLocZ;
	private GLSLUtils util = new GLSLUtils();
	private int brickTexture;
	private Texture joglBrickTexture;

	private	MatrixStack mvStack = new MatrixStack(20);

	public Exercise_5_3()
	{	setTitle("Chapter4 - program4");
		setSize(600, 600);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		getContentPane().add(myCanvas);
		this.setVisible(true);
		FPSAnimator animator = new FPSAnimator(myCanvas, 50);
		animator.start();
	}

	public void display(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();

		gl.glClear(GL_DEPTH_BUFFER_BIT);
		float bkg[] = { 0.0f, 0.0f, 0.0f, 1.0f };
		FloatBuffer bkgBuffer = Buffers.newDirectFloatBuffer(bkg);
		gl.glClearBufferfv(GL_COLOR, 0, bkgBuffer);

		gl.glClear(GL_DEPTH_BUFFER_BIT);

		gl.glUseProgram(rendering_program);

		int mv_loc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
		int proj_loc = gl.glGetUniformLocation(rendering_program, "proj_matrix");

		float aspect = (float) myCanvas.getWidth() / (float) myCanvas.getHeight();
		Matrix3D pMat = perspective(60.0f, aspect, 0.1f, 1000.0f);

		// push view matrix onto the stack
		mvStack.pushMatrix();
		mvStack.translate(-cameraX, -cameraY, -cameraZ);
		double amt = (double)(System.currentTimeMillis())/1000.0;

		gl.glUniformMatrix4fv(proj_loc, 1, false, pMat.getFloatValues(), 0);

		// ----------------------  pyramid == sun
		mvStack.pushMatrix();
		mvStack.translate(pyrLocX, pyrLocY, pyrLocZ);
		mvStack.pushMatrix();
		mvStack.rotate((System.currentTimeMillis())/10.0,1.0,0.0,0.0);
		gl.glUniformMatrix4fv(mv_loc, 1, false, mvStack.peek().getFloatValues(), 0);
		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
		gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[2]);
		gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(1);

		gl.glEnable(GL_DEPTH_TEST);

		gl.glActiveTexture(GL_TEXTURE0);
		gl.glBindTexture(GL_TEXTURE_2D, brickTexture);

		gl.glDrawArrays(GL_TRIANGLES, 0, 18);
		mvStack.popMatrix();

		//-----------------------  cube == planet
		mvStack.pushMatrix();
		mvStack.translate(Math.sin(amt)*4.0f, 0.0f, Math.cos(amt)*4.0f);
		mvStack.pushMatrix();
		mvStack.rotate((System.currentTimeMillis())/10.0,0.0,1.0,0.0);
		gl.glUniformMatrix4fv(mv_loc, 1, false, mvStack.peek().getFloatValues(), 0);
		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[3]);
		gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(1);

		gl.glActiveTexture(GL_TEXTURE0);
		gl.glBindTexture(GL_TEXTURE_2D, brickTexture);

		gl.glDrawArrays(GL_TRIANGLES, 0, 36);
		mvStack.popMatrix();

		//-----------------------  smaller cube == moon
		mvStack.pushMatrix();
		mvStack.translate(0.0f, Math.sin(amt)*2.0f, Math.cos(amt)*2.0f);
		mvStack.rotate((System.currentTimeMillis())/10.0,0.0,0.0,1.0);
		mvStack.scale(0.25, 0.25, 0.25);
		gl.glUniformMatrix4fv(mv_loc, 1, false, mvStack.peek().getFloatValues(), 0);
		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[2]);
		gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(1);

		gl.glActiveTexture(GL_TEXTURE0);
		gl.glBindTexture(GL_TEXTURE_2D, brickTexture);

		gl.glDrawArrays(GL_TRIANGLES, 0, 36);
		mvStack.popMatrix();  mvStack.popMatrix();  mvStack.popMatrix();
		mvStack.popMatrix();
	}

	public void init(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();
		rendering_program = createShaderProgram();
		setupVertices();
		cameraX = 0.0f; cameraY = 0.0f; cameraZ = 12.0f;
		cubeLocX = 0.0f; cubeLocY = 0.0f; cubeLocZ = 0.0f;
		pyrLocX = 0.0f; pyrLocY = 0.0f; pyrLocZ = 0.0f;

		joglBrickTexture = loadTexture("brick1.jpg");
		brickTexture = joglBrickTexture.getTextureObject();
	}

	private void setupVertices()
	{	GL4 gl = (GL4) GLContext.getCurrentGL();
		float[] cube_positions =
		{	-1.0f,  1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f,
			1.0f, -1.0f, -1.0f, 1.0f,  1.0f, -1.0f, -1.0f,  1.0f, -1.0f,
			1.0f, -1.0f, -1.0f, 1.0f, -1.0f,  1.0f, 1.0f,  1.0f, -1.0f,
			1.0f, -1.0f,  1.0f, 1.0f,  1.0f,  1.0f, 1.0f,  1.0f, -1.0f,
			1.0f, -1.0f,  1.0f, -1.0f, -1.0f,  1.0f, 1.0f,  1.0f,  1.0f,
			-1.0f, -1.0f,  1.0f, -1.0f,  1.0f,  1.0f, 1.0f,  1.0f,  1.0f,
			-1.0f, -1.0f,  1.0f, -1.0f, -1.0f, -1.0f, -1.0f,  1.0f,  1.0f,
			-1.0f, -1.0f, -1.0f, -1.0f,  1.0f, -1.0f, -1.0f,  1.0f,  1.0f,
			-1.0f, -1.0f,  1.0f,  1.0f, -1.0f,  1.0f,  1.0f, -1.0f, -1.0f,
			1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f,  1.0f,
			-1.0f,  1.0f, -1.0f, 1.0f,  1.0f, -1.0f, 1.0f,  1.0f,  1.0f,
			1.0f,  1.0f,  1.0f, -1.0f,  1.0f,  1.0f, -1.0f,  1.0f, -1.0f
		};

		float[] pyramid_positions =
		{	-1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 0.0f,    //front
			1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 0.0f, 1.0f, 0.0f,    //right
			1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 0.0f, 1.0f, 0.0f,  //back
			-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 0.0f,  //left
			-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, //LF
			1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f  //RR
		};

		float[] texture_coordinates_pyramid =
		{	0.0f, 0.0f, 1.0f, 0.0f, 0.5f, 1.0f,
			0.0f, 0.0f, 1.0f, 0.0f, 0.5f, 1.0f,
			0.0f, 0.0f, 1.0f, 0.0f, 0.5f, 1.0f,
			0.0f, 0.0f, 1.0f, 0.0f, 0.5f, 1.0f,
			0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
			1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f
		};

		float[] texture_coordinates_cube =
		{	.25f,  .666666666f, .25f, .3333333333f, .5f, .3333333333f,	// front face lower left
			.5f, .333333333333f, .5f,  .66666666666f, .25f,  .66666666666f,	// front face upper right
			.5f, .3333333333f, .75f, .33333333333f,  .5f,  .6666666666f,	// right face lower left
			.75f, .33333333333f,  .75f,  .66666666666f, .5f,  .6666666666f,	// right face upper right
			.75f, .3333333333f,  1.0f, .3333333333f, .75f,  .66666666666f,	// back face lower
			1.0f, .3333333333f, 1.0f,  .6666666666f, .75f,  .6666666666f,	// back face upper
			0.0f, .333333333f,  .25f, .333333333f, 0.0f,  .666666666f,	// left face lower
			.25f, .333333333f, .25f,  .666666666f, 0.0f,  .666666666f,	// left face upper
			.25f, 0.0f,  .5f, 0.0f,  .5f, .333333333f,			// bottom face front
			.5f, .333333333f, .25f, .333333333f, .25f, 0.0f,		// bottom face back
			.25f,  .666666666f, .5f,  .666666666f, .5f,  1.0f,		// top face back
			.5f,  1.0f,  .25f,  1.0f, .25f,  .666666666f			// top face front
		};

		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
		gl.glGenBuffers(vbo.length, vbo, 0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		FloatBuffer cubeBuf = Buffers.newDirectFloatBuffer(cube_positions);
		gl.glBufferData(GL_ARRAY_BUFFER, cubeBuf.limit()*4, cubeBuf, GL_STATIC_DRAW);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
		FloatBuffer pyrBuf = Buffers.newDirectFloatBuffer(pyramid_positions);
		gl.glBufferData(GL_ARRAY_BUFFER, pyrBuf.limit()*4, pyrBuf, GL_STATIC_DRAW);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[2]);
		FloatBuffer tex_pyrBuf = Buffers.newDirectFloatBuffer(texture_coordinates_pyramid);
		gl.glBufferData(GL_ARRAY_BUFFER, tex_pyrBuf.limit()*4, tex_pyrBuf, GL_STATIC_DRAW);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[3]);
		FloatBuffer tex_cubBuf = Buffers.newDirectFloatBuffer(texture_coordinates_cube);
		gl.glBufferData(GL_ARRAY_BUFFER, tex_cubBuf.limit()*4, tex_cubBuf, GL_STATIC_DRAW);
	}

	private Matrix3D perspective(float fovy, float aspect, float n, float f)
	{	float q = 1.0f / ((float) Math.tan(Math.toRadians(0.5f * fovy)));
		float A = q / aspect;
		float B = (n + f) / (n - f);
		float C = (2.0f * n * f) / (n - f);
		Matrix3D r = new Matrix3D();
		r.setElementAt(0,0,A);
		r.setElementAt(1,1,q);
		r.setElementAt(2,2,B);
		r.setElementAt(3,2,-1.0f);
		r.setElementAt(2,3,C);
		return r;
	}

	public static void main(String[] args) { new Exercise_5_3(); }
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	public void dispose(GLAutoDrawable drawable) {}

	private int createShaderProgram()
	{	GL4 gl = (GL4) GLContext.getCurrentGL();

		String vshaderSource[] = util.readShaderSource("vert.shader_exercise_5_3.txt");
		String fshaderSource[] = util.readShaderSource("frag.shader_exercise_5_3.txt");

		int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
		int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);

		gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0);
		gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0);

		gl.glCompileShader(vShader);
		gl.glCompileShader(fShader);

		int vfprogram = gl.glCreateProgram();
		gl.glAttachShader(vfprogram, vShader);
		gl.glAttachShader(vfprogram, fShader);
		gl.glLinkProgram(vfprogram);
		return vfprogram;
	}


	public Texture loadTexture(String textureFileName)
	{	Texture tex = null;
		try { tex = TextureIO.newTexture(new File(textureFileName), false); }
		catch (Exception e) { e.printStackTrace(); }
		return tex;
	}
}