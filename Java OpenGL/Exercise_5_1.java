import graphicslib3D.*;
import java.io.*;
import java.nio.*;
import javax.swing.*;
import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES3.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.*;
import com.jogamp.common.nio.Buffers;

public class Exercise_5_1 extends JFrame implements GLEventListener
{	private GLCanvas myCanvas;
	private int rendering_program;
	private int vao[] = new int[1];
	private int vbo[] = new int[2];
	private float cameraX, cameraY, cameraZ;
	private float pyrLocX, pyrLocY, pyrLocZ;
	private GLSLUtils util = new GLSLUtils();
	private int brickTexture;
	private Texture joglBrickTexture;

	public Exercise_5_1()
	{	setTitle("Chapter5 - program1");
		setSize(800, 800);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		getContentPane().add(myCanvas);
		this.setVisible(true);
	}

	public void display(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();

		gl.glClear(GL_DEPTH_BUFFER_BIT);

		gl.glUseProgram(rendering_program);

		int mv_loc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
		int proj_loc = gl.glGetUniformLocation(rendering_program, "proj_matrix");

		float aspect = (float) myCanvas.getWidth() / (float) myCanvas.getHeight();
		Matrix3D pMat = perspective(60.0f, aspect, 0.1f, 1000.0f);

		Matrix3D vMat = new Matrix3D();
		vMat.translate(-cameraX, -cameraY, -cameraZ);

		Matrix3D mMat = new Matrix3D();
		mMat.translate(pyrLocX, pyrLocY, pyrLocZ);
		mMat.rotateX(-25.0f);
		mMat.rotateY(35.0f);

		Matrix3D mvMat = new Matrix3D();
		mvMat.concatenate(vMat);
		mvMat.concatenate(mMat);

		gl.glUniformMatrix4fv(mv_loc, 1, false, mvMat.getFloatValues(), 0);
		gl.glUniformMatrix4fv(proj_loc, 1, false, pMat.getFloatValues(), 0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
		gl.glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(1);

		gl.glActiveTexture(GL_TEXTURE0);
		gl.glBindTexture(GL_TEXTURE_2D, brickTexture);

		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);

		gl.glDrawArrays(GL_TRIANGLES, 0, 18);
	}

	public void init(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();

		rendering_program = createShaderProgram();
		setupVertices();

		cameraX = 0.0f; cameraY = 0.0f; cameraZ = 4.0f;
		pyrLocX = 0.0f; pyrLocY = 0.0f; pyrLocZ = 0.0f;

		joglBrickTexture = loadTexture("brick1.jpg");
		brickTexture = joglBrickTexture.getTextureObject();
	}

	private void setupVertices()
	{	GL4 gl = (GL4) GLContext.getCurrentGL();

		float[] pyramid_positions =
		{	-1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 0.0f,   //front
			1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 0.0f, 1.0f, 0.0f,   //right
			1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 0.0f, 1.0f, 0.0f, //back
			-1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 0.0f, //left
			-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, //LF
			1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f  //RR
		};
		float[] texture_coordinates =
		{	0.0f, 0.0f, 1.0f, 0.0f, 0.5f, 1.0f,
			0.0f, 0.0f, 1.0f, 0.0f, 0.5f, 1.0f,
			0.0f, 0.0f, 1.0f, 0.0f, 0.5f, 1.0f,
			0.0f, 0.0f, 1.0f, 0.0f, 0.5f, 1.0f,
			0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f,
			1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f
		};

		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
		gl.glGenBuffers(vbo.length, vbo, 0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		FloatBuffer pyrBuf = Buffers.newDirectFloatBuffer(pyramid_positions);
		gl.glBufferData(GL_ARRAY_BUFFER, pyrBuf.limit()*4, pyrBuf, GL_STATIC_DRAW);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[1]);
		FloatBuffer texBuf = Buffers.newDirectFloatBuffer(texture_coordinates);
		gl.glBufferData(GL_ARRAY_BUFFER, texBuf.limit()*4, texBuf, GL_STATIC_DRAW);
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
		r.setElementAt(3,3,0.0f);
		return r;
	}

	public static void main(String[] args) { new Exercise_5_1(); }
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	public void dispose(GLAutoDrawable drawable) {}

	private int createShaderProgram()
	{	GL4 gl = (GL4) GLContext.getCurrentGL();

		String vshaderSource[] = util.readShaderSource("vert.shader_exercise_5_1.txt");
		String fshaderSource[] = util.readShaderSource("frag.shader_exercise_5_1.txt");

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