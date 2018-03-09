import graphicslib3D.*;
import graphicslib3D.GLSLUtils.*;
import java.nio.*;
import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GLContext;

public class Exercise_4_1_and_4_2 extends JFrame implements GLEventListener
{	private GLCanvas myCanvas;
	private int rendering_program;
	private int vao[] = new int[1];
	private int vbo[] = new int[2];
	private float cameraX, cameraY, cameraZ;
	private float camRotX, camRotY, camRotZ;
	private float cubeLocX, cubeLocY, cubeLocZ;
	private GLSLUtils util = new GLSLUtils();

	public Exercise_4_1_and_4_2()
	{	setTitle("Chapter4 - program1a");
		setSize(600, 600);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		getContentPane().add(myCanvas);
		this.setVisible(true);
		//FPSAnimator animator = new FPSAnimator(myCanvas, 50);
		//animator.start();
	}

	public void display(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();

		gl.glClear(GL_DEPTH_BUFFER_BIT);

		float bkg[] = {0.0f, 0.0f, 0.0f, 1.0f};
		FloatBuffer bkgBuffer = Buffers.newDirectFloatBuffer(bkg);
		gl.glClearBufferfv(GL_COLOR, 0,  bkgBuffer);

		gl.glUseProgram(rendering_program);

		int mv_loc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
		int proj_loc = gl.glGetUniformLocation(rendering_program, "proj_matrix");

		float aspect = (float) myCanvas.getWidth() / (float) myCanvas.getHeight();
		Matrix3D pMat = perspective(60.0f, aspect, 0.1f, 1000.0f);

		Matrix3D vMat = new Matrix3D();
		//vMat.rotate(-camRotX, -camRotY, -camRotZ);
		vMat.translate(-cameraX, -cameraY, -cameraZ);
		//vMat.concatenate(vMat);

		Matrix3D mMat = new Matrix3D();
		//double t = (double) (System.currentTimeMillis())/10000.0;

		mMat.translate(cubeLocX, cubeLocY, cubeLocZ);
		//mMat.rotate(1000*t,1000*t,1000*t);

		Matrix3D mvMat = new Matrix3D();
		mvMat.concatenate(vMat);
		mvMat.concatenate(mMat);

		gl.glUniformMatrix4fv(mv_loc, 1, false, mvMat.getFloatValues(), 0);
		gl.glUniformMatrix4fv(proj_loc, 1, false, pMat.getFloatValues(), 0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(0);

		gl.glEnable(GL_DEPTH_TEST);
		gl.glDepthFunc(GL_LEQUAL);

		gl.glDrawArrays(GL_TRIANGLES, 0, 54);
	}

	public void init(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) drawable.getGL();
		rendering_program = createShaderProgram();
		setupVertices();
		cameraX = 0.0f; cameraY = 0.0f; cameraZ = 8.0f;
		camRotX = 2.0f; camRotY = 4.0f; camRotZ = 8.0f;
		cubeLocX = 0.0f; cubeLocY = 2.0f; cubeLocZ = 0.0f;
	}

	private void setupVertices()
	{	GL4 gl = (GL4) GLContext.getCurrentGL();
		float[] vertex_positions =
		{	-1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, //front triangle
			-1.0f, -1.0f, -1.5f, 1.0f, -1.0f, -1.5f, 0.0f, 1.0f, -1.5f, //back triangle
			1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.5f, //right side
			1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.5f, 0.0f, 1.0f, -1.5f, //right side
			-1.0f, -1.0f, -1.5f, -1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, //left side
			-1.0f, -1.0f, -1.5f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, -1.5f, //left side
			//no base - it still works so okay! Plus I was having problems putting a base in.
		};

		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
		gl.glGenBuffers(vbo.length, vbo, 0);

		gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
		FloatBuffer vertBuf = Buffers.newDirectFloatBuffer(vertex_positions);
		gl.glBufferData(GL_ARRAY_BUFFER, vertBuf.limit()*4, vertBuf, GL_STATIC_DRAW);
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

	public static void main(String[] args) { new Exercise_4_1_and_4_2(); }
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	public void dispose(GLAutoDrawable drawable) {}

	private int createShaderProgram()
	{	GL4 gl = (GL4) GLContext.getCurrentGL();

		String vshaderSource[] = util.readShaderSource("vert.shader_exercise_4_1.txt");
		String fshaderSource[] = util.readShaderSource("frag.shader_exercise_4_1.txt");

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
}