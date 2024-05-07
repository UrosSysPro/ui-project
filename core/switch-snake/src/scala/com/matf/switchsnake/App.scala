package com.matf.switchsnake

import com.matf.ui.utils.animation.{Animatable, AnimationController, AnimationStates}
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.{Colors, MainAxisAlignment}
import com.matf.ui.utils.font.Fonts
import com.matf.ui.widgets.cupertino.Switch
import com.matf.ui.widgets._
import com.matf.ui.{Scene, Widget}
import com.systemvi.engine.application.Game
import com.systemvi.engine.utils.Utils
import com.systemvi.engine.utils.Utils.Buffer
import com.systemvi.engine.window.Window
import org.lwjgl.glfw.GLFW

class App extends Game(3,3,60,800,600,"Switch Snake"){
  var scene:Scene=null
  override def setup(window: Window): Unit = {
    scene=Scene(
      window = window,
      root=new SnakeGame(),
      font = Fonts.pixels
    )
    setInputProcessor(scene)
  }

  override def loop(delta: Float): Unit = {
    Utils.clear(0,0,0,1,Buffer.COLOR_BUFFER)
    scene.animate(delta)
    scene.resize(scene.width,scene.height)
    scene.draw()
  }
}


class SnakeGame extends StatefulWidget{
  override def createState(): State = new SnakeGameState()
}
class SnakeGameState extends State with Animatable{
  val width: Int = 10
  val height: Int = 10
  val snake: Snake = new Snake()
  snake.init(width,height)
  for(i<-0 to 5)snake.add()
  var controller:AnimationController=null

  override def init(): Unit = {
    controller=AnimationController(
      animatable = this,
      milliseconds = 100,
      onStateChange = state => if(state == AnimationStates.end){
        setState{()=>
          controller.value = 0
          controller.setState(AnimationStates.running)
          snake.update()
          snake.checkGameBorder(width,height)
          if(snake.checkSelfCollision())println("game over")
        }
      }
    )
    controller.setState(AnimationStates.running)
  }
  override def build(context: BuildContext): Widget ={
    GestureDetector(
      mouseDown=(_,_,_,_)=>true,
      keyDown=(key,_,_)=>{
        key match {
          case GLFW.GLFW_KEY_UP => snake.vx = 0; snake.vy = -1
          case GLFW.GLFW_KEY_DOWN => snake.vx = 0; snake.vy = 1
          case GLFW.GLFW_KEY_LEFT => snake.vx = -1; snake.vy = 0
          case GLFW.GLFW_KEY_RIGHT => snake.vx = 1; snake.vy = 0
          case _ =>
        }
        true
      },
      child=Container(
        color = Colors.white,
        child = Row(
          mainAxisAlignment=MainAxisAlignment.start,
          children = (0 until width).map{ i=>
            SizedBox(
              width=55,
              child=Column(
                children = (0 until height).map{j=>
                  Switch(value = snake.parts.find(part=> part.x==i && part.y==j) match {
                    case Some(_) => true
                    case None => false
                  })
                }.toArray
              )
            )
          }.toArray
        )
      )
    )
  }
}
