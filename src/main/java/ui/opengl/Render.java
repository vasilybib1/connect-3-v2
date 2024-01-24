package ui.opengl;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.*;
import java.nio.file.Files;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.system.MemoryStack.*;

import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.GLFW.*;

public class Render{
  private static final int HEIGHT = 800;
  private static final int WIDTH = 800;
  private static final float[] BACKGROUND_COLOR = {0.2f, 0.3f, 0.3f, 0.1f};

  public static void makeWindow(){
    glfwInit();
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
    
    long window = glfwCreateWindow(HEIGHT, WIDTH, "connect", NULL, NULL);
    
    if(window == NULL){
      System.out.println("failed to craete GLFW window");
      glfwTerminate();
    }
    
    glfwMakeContextCurrent(window);
    GL.createCapabilities();
    
    while(!glfwWindowShouldClose(window)){
    
      if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS){
        glfwSetWindowShouldClose(window, true);
      }
      glClearColor(BACKGROUND_COLOR[0], BACKGROUND_COLOR[1], BACKGROUND_COLOR[2], BACKGROUND_COLOR[3]);
      glClear(GL_COLOR_BUFFER_BIT);
      glfwSwapBuffers(window);
      glfwPollEvents();
    }

  }
}
