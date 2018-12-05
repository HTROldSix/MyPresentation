package com.example.presentation.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.KeyEvent;

import java.nio.ByteBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRootView extends GLSurfaceView {
    GLSurfaceView.Renderer mRenderer;
    boolean DEBUG_FPS = false;

    public GLRootView(Context context) {
        super(context);
    }

    public GLRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRenderer(new GLRender());
    }

    @Override
    public void setRenderer(Renderer renderer) {
        if (renderer == null) return;
        mRenderer = renderer;
        super.setRenderer(renderer);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
            queueEvent(new Runnable() {
                //这个方法会在渲染线程里被调用    
                public void run() {
                    //mRenderer.handleDpadCenter();
                }
            });
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class GLRender implements GLSurfaceView.Renderer {
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //关闭抗抖动
            gl.glDisable(GL10.GL_DITHER);
            //设置系统对透视进行修正
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
            gl.glClearColor(0, 0, 0, 0);
            //设置阴影平滑模式
            gl.glShadeModel(GL10.GL_SMOOTH);
            //启动深度测试
            gl.glEnable(GL10.GL_DEPTH_TEST);
            //设置深度测试的类型
            gl.glDepthFunc(GL10.GL_LEQUAL);

            if (DEBUG_FPS) {
                setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
            } else {
                setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
            }
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //设置3D视窗的大小及位置
            gl.glViewport(0, 0, width, height);
            //将当前矩阵模式设为投影矩形
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //初始化单位矩阵
            gl.glLoadIdentity();
            //计算透视窗口的宽度高度比
            float ratio = (float) width / height;
            //调用此方法设置透视窗口的空间大小
            gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            //清除屏幕缓存和深度缓存
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            //启用顶点坐标数据
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            //启用顶点颜色数据
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            //设置当前矩阵堆栈为模型堆栈
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.gl
            //绘制结束
            gl.glFinish();
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        }
    }
}
