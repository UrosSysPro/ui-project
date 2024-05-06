package com.matf.dancingrange

import com.matf.ui.utils.animation.{Animatable, AnimationController, AnimationStates}
import com.matf.ui.utils.context.BuildContext
import com.matf.ui.utils.data.{Colors, MainAxisAlignment}
import com.matf.ui.widgets.{material, _}
import com.matf.ui.{Scene, Widget}
import com.systemvi.engine.application.Game
import com.systemvi.engine.ui.utils.font.Font
import com.systemvi.engine.utils.Utils
import com.systemvi.engine.utils.Utils.Buffer
import com.systemvi.engine.window.Window

class App extends Game(3,3,60,800,600,"Switch Snake"){
  var scene:Scene=null
  override def setup(window: Window): Unit = {
    scene=Scene(
      window = window,
      root=new DancingRange(),
      font = Font.load("assets/font.PNG","assets/font.json")
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


class DancingRange extends StatefulWidget{
  override def createState(): State = new DancingRangeState()
}
class DancingRangeState extends State with Animatable{
  var controller:AnimationController=null
  val width=4
  val height=20
  override def init(): Unit = {
    controller=AnimationController(
      animatable = this,
      seconds = 5,
      onStateChange = state => if(state == AnimationStates.end){
        setState{()=>
          controller.value = 0
          controller.setState(AnimationStates.running)
        }
      },
      onValueChange = _=>setState(()=>{})
    )
    controller.setState(AnimationStates.running)
  }
  override def build(context: BuildContext): Widget ={
      Container(
        color = Colors.white,
        child = Row(
          mainAxisAlignment=MainAxisAlignment.start,
          children = (0 until width).map{ i=>
            SizedBox(
              width = 200,
              child = Column(
                children = (0 until height).map{j=>
                  SizedBox(
                    height = 30,
                    child = material.Range(
                      Math.sin(
                        controller.value*Math.PI*2+
                          j.toFloat/5+
                          i%2*Math.PI
                      ).toFloat,
                      1,
                      -1,
                      0.001f,
                      onChange = _ => {}
                    )
                  )
                }.toArray
              )
            )
          }.toArray
        )

    )
  }
}
