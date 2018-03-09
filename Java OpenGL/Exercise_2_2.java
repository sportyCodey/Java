import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLContext;
import com.jogamp.common.nio.Buffers;

import graphicslib3D.*;
import graphicslib3D.GLSLUtils.*;

public class Exercise_2_2 extends JFrame implements GLEventListener
{	private GLCanvas myCanvas;
	private int rendering_program;
	private int vao[] = new int[1];
	private GLSLUtils util = new GLSLUtils();

	public Exercise_2_2()
	{	setTitle("Chapter2 - program5");
		setSize(400, 200);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		this.add(myCanvas);
		setVisible(true);
	}

	public void display(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();
		gl.glUseProgram(rendering_program);
		gl.glDrawArrays(GL_TRIANGLES,0,3);
	}

	public void init(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();
		rendering_program = createShaderProgram();
		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
	}

	private int createShaderProgram()
	{	GL4 gl = (GL4) GLContext.getCurrentGL();

		String vshaderSource[] = util.readShaderSource("vert_shader_exercise_2_2.txt");
		String fshaderSource[] = util.readShaderSource("frag_shader_exercise_2_2.txt");

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

	public static void main(String[] args) { new Exercise_2_2(); }
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	public void dispose(GLAutoDrawable drawable) {}
}