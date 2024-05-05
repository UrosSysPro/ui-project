package com.matf.ui

import com.matf.ui.utils.context.{BuildContext, DrawContext}
import com.matf.ui.utils.data.Colors
import com.matf.ui.utils.tree.{Animator, EventListenerFinder, TreeBuilder}
import com.matf.ui.widgets.{GestureDetector, State}
import com.systemvi.engine.camera.Camera3
import com.systemvi.engine.ui.utils.font.Font
import com.systemvi.engine.utils.Utils
import com.systemvi.engine.window.{InputProcessor, Window}
import org.joml.{Matrix4f, Vector2f}

class Scene(val root:Widget,window:Window,val font:Font) extends InputProcessor{
  //screen info
  var width: Int =window.getWidth
  var height: Int =window.getHeight
  //Scene state
  val states:Map[String, State]=Map[String,State]()
  val camera:Camera3=Camera3.builder2d().build()
  val renderer:WidgetRenderer2=new WidgetRenderer2(camera,font)
  //event listeners
  var focused:GestureDetector=null
  val mouse=new Vector2f()
  //three utils
  val eventListenerFinder=new EventListenerFinder()
  val threeBuilder=new TreeBuilder(states)
  val context=new BuildContext()
  val drawContext=DrawContext(renderer,new Matrix4f())
  val animator:Animator=new Animator()
  //initial build
  threeBuilder.build(root,s"/${root.getClass.getSimpleName}",context)

  resize(window.getWidth,window.getHeight)
  def resize(width:Int,height:Int): Boolean = {
    this.width=width
    this.height=height
    root.calculateSize(new Vector2f(width,height))
    root.calculatePosition(new Vector2f(0,0))
    renderer.camera.orthographic(-width/2,width/2,-height/2,height/2,0,100)
    renderer.camera.position(width/2,height/2,0)
    renderer.camera.update()
    true
  }

  def animate(delta:Float): Unit = {
    animator.animate(root,delta)
  }
  def draw():Unit={
    Utils.enableBlending()
    root.draw(drawContext)
    renderer.flush()
    Utils.disableBlending()
  }

  override def keyDown(key: Int, scancode: Int, mods: Int): Boolean = {
    if(focused!=null)return focused.keyDown(key,scancode,mods)
    false
  }
  override def keyUp(key: Int, scancode: Int, mods: Int): Boolean = {
    if(focused!=null)return focused.keyUp(key,scancode,mods)
    false
  }
  override def mouseDown(button: Int, mods: Int, x: Double, y: Double): Boolean = {
    mouse.set(x,y)
    var eventAccepted=false
    val stack=eventListenerFinder.find(root,mouse)
    while(!eventAccepted&&stack.nonEmpty){
      val detector=stack.pop()
      if(detector.mouseDown(button,mods,x-detector.position.x,y-detector.position.y)) {
        if(detector.focusable)focused=detector
        eventAccepted=true
      }
    }
    if(!eventAccepted)focused=null
    eventAccepted
  }
  override def mouseUp(button: Int, mods: Int, x: Double, y: Double): Boolean = {
    if(focused!=null)return focused.mouseUp(button,mods,x-focused.position.x,y-focused.position.y)
    false
  }
  override def mouseMove(x: Double, y: Double): Boolean = {
    mouse.set(x,y)
    //mouse enter
    var stack=eventListenerFinder.find(root)
    while (stack.nonEmpty){
      val widget=stack.pop()
      if(!widget.mouseOver&&widget.contains(mouse.x,mouse.y)){
        widget.mouseOver=true
        widget.mouseEnter()
      }
    }
    //mouse leave
    stack=eventListenerFinder.find(root)
    while (stack.nonEmpty){
      val widget=stack.pop()
      if(widget.mouseOver&& !widget.contains(mouse.x,mouse.y)){
        widget.mouseOver=false
        widget.mouseLeave()
      }
    }
    //mouse move
    var eventAccepted=false
    stack=eventListenerFinder.find(root,mouse)
    while(!eventAccepted&&stack.nonEmpty){
      val detector=stack.pop()
      if(detector.mouseMove(x-detector.position.x,y-detector.position.y))eventAccepted=true
    }
    eventAccepted
  }
  override def scroll(offsetX: Double, offsetY: Double): Boolean = {
    var eventAccepted=false
    val stack=eventListenerFinder.find(root,mouse)
    while(!eventAccepted&&stack.nonEmpty){
      val detector=stack.pop()
      if(detector.scroll(offsetX,offsetY))eventAccepted=true
    }
    eventAccepted
  }
}

object Scene{
  def apply(root: Widget, window: Window,font:Font): Scene = {
    new Scene(root, window,font)
  }
}