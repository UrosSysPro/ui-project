package com.matf.switchsnake

import com.matf.ui.utils.animation.{Animatable, AnimationController, AnimationStates}
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.{Colors, MainAxisAlignment}
import com.matf.ui.widgets.cupertino.Switch
import com.matf.ui.widgets._
import com.matf.ui.Widget
import org.lwjgl.glfw.GLFW

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
