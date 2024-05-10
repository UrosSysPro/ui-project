package com.matf.switchsnake

import com.matf.ui.utils.animation.{Animatable, AnimationController, AnimationStates}
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.{AxisSize, BoxDecoration, BoxShadow, Colors, CrossAxisAlignment, MainAxisAlignment}
import com.matf.ui.widgets.cupertino.Switch
import com.matf.ui.widgets._
import com.matf.ui.Widget
import com.matf.ui.utils.font.Fonts
import com.matf.ui.widgets.material.Button
import org.joml.{Vector2f, Vector4f}
import org.lwjgl.glfw.GLFW

import scala.util.Random

class SnakeGame extends StatefulWidget{
  override def createState(): State = new SnakeGameState()
}
class SnakeGameState extends State with Animatable{
  val width: Int = 14
  val height: Int = 20
  val snake: Snake = new Snake()
  snake.init(width,height)
  for(_ <-0 to 5)snake.add()
  var controller:AnimationController=null
  var gameOver=false
  val random=new Random()
  val food=SnakePart(random.nextInt(width),random.nextInt(height))

  override def init(): Unit = {
    controller=AnimationController(
      animatable = this,
      milliseconds = 100,
      onStateChange = state => if(state == AnimationStates.end){
        setState{()=>
          snake.update()
          snake.checkGameBorder(width,height)
          if(snake.checkSelfCollision())gameOver=true
          if(snake.parts.head.x==food.x&& snake.parts.head.y==food.y){
            snake.add()
            food.x=random.nextInt(width)
            food.y=random.nextInt(height)
          }
          if(!gameOver){
            controller.value = 0
            controller.setState(AnimationStates.running)
          }else{
            println("game over")
          }
        }
      }
    )
    controller.setState(AnimationStates.running)
  }
  override def build(context: BuildContext): Widget ={
    Stack(
      children = Array(
        GestureDetector(
          mouseDown=(_,_,_,_)=>true,
          keyDown=(key,_,_)=>{
            key match {
              case GLFW.GLFW_KEY_UP    => if(snake.vy !=  1){ snake.vx =  0; snake.vy = -1 }
              case GLFW.GLFW_KEY_DOWN  => if(snake.vy != -1){ snake.vx =  0; snake.vy =  1 }
              case GLFW.GLFW_KEY_LEFT  => if(snake.vx !=  1){ snake.vx = -1; snake.vy =  0 }
              case GLFW.GLFW_KEY_RIGHT => if(snake.vx != -1){ snake.vx =  1; snake.vy =  0 }
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
                      Switch(
                        value = (snake.parts.find(part=> part.x==i && part.y==j) match {
                        case Some(_) => true
                        case None => false
                      }) || (food.x==i && food.y==j),
                        backgroundColor = if(food.x==i && food.y==j)Colors.red500 else Colors.green400
                      )
                    }.toArray
                  )
                )
              }.toArray
            )
          )
        )    ,
        if(gameOver)Container(
          color = new Vector4f(0,0,0,0.3f),
          child = Center(
            child = Container(
              decoration=BoxDecoration(
                color=Colors.white,
                borderRadius=20,
                boxShadow = Array(
                  BoxShadow(
                    color=new Vector4f(0,0,0,0.3f),
                    offset=new Vector2f(0,5),
                    size=10,
                    blur=10
                  )
                )
              ),
              child = Padding(
                padding=EdgeInsets.all(30),
                child=Column(
                  mainAxisSize=AxisSize.fit,crossAxisSize=AxisSize.fit,
                  crossAxisAlignment=CrossAxisAlignment.center,
                  children = Array(
                    Text("Game over", font = Fonts.pixels),
                    SizedBox(width=0,height=50),
                    Button.filled(
                      decoration = BoxDecoration(
                        color=Colors.green600,
                        borderRadius=20,
                        boxShadow = Array(
                          BoxShadow(
                            color=new Vector4f(0,0,0,0.3f),
                            size=10,
                            offset=new Vector2f(0,3),
                            blur=10
                          )
                        )
                      ),
                      onTap = ()=>setState{() =>
                        gameOver=false
                        snake.init(width, height)
                        for(_<-0 until 5)snake.add()
                        controller.value=0
                        controller.setState(AnimationStates.running)
                      },
                      child = SizedBox(
                        width=300,
                        height=50,
                        child=Center(
                          child=Text("Restart",style=TextStyle(color = Colors.white),font=Fonts.pixels)
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        )
        else Container()
      )
    )
  }
}
