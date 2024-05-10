package com.matf.ui.widgets.cupertino

import com.systemvi.engine.math.Bezier2f
import com.matf.ui.Widget
import com.matf.ui.utils.animation.{Animatable, AnimationController, AnimationStates}
import com.matf.ui.utils.context.{BuildContext, DrawContext}
import com.matf.ui.utils.data.Colors
import com.matf.ui.widgets.cupertino.Switch.padding
import com.matf.ui.widgets.{GestureDetector, SizedBox, State, StatefulWidget}
import org.joml.{Vector2f, Vector4f}

class Switch(
              val value:Boolean,
              val onChange:Boolean=>Unit,
              val backgroundColor:Vector4f,
              val unselectedBackgroundColor:Vector4f,
              val circleColor:Vector4f
            ) extends StatefulWidget {
  override def createState(): State = new SwitchState()
}

class SwitchState extends State with Animatable{

  var controller:AnimationController=null
  var timing = new Bezier2f(Array(
    new Vector2f(0f,0f),
    new Vector2f(0.35f,.86f),
    new Vector2f(0f,1.01f),
    new Vector2f(1.0f)
  ))

  override def init(): Unit = {
    controller= AnimationController(
      animatable = this,
      milliseconds=200
    )
  }
  override def build(context:BuildContext): Widget = {
    widget match {
      case switch: Switch=>
        if(!switch.value && controller.getState == AnimationStates.end)controller.setState(AnimationStates.reverse)
        if( switch.value && controller.getState == AnimationStates.start)controller.setState(AnimationStates.running)
    }
    SizedBox(
      width=55,height=30,
      child = GestureDetector(
        mouseDown = (_,_,_,_)=>{
          val switch=widget match {
            case switch: Switch=>switch
          }
          if(switch.onChange!=null){
            switch.onChange(!switch.value)
            true
          }else{
            false
          }
        }
      )
    )
  }

  override def draw(context:DrawContext): Unit = widget match{case widget:Switch=>
    val d = timing.get(controller.value).y
    val size=widget.size
    val position=widget.position
    val circleSize:Float = size.y
    val x:Float =  d*(position.x + size.x - circleSize)+ (1-d)*position.x
    val y:Float = position.y
    //background
    context.renderer.rect(
      position.x,
      position.y,
      size.x,
      size.y,
      new Vector4f()
        .add(new Vector4f(widget.backgroundColor).mul(d))
        .add(new Vector4f(widget.unselectedBackgroundColor).mul(1-d)),
      size.y/2,blur = 1,
      context
    )
    val shadowBlur:Float=4
    val shadowSize:Float=4
    //shadow
    context.renderer.rect(
      x+padding-shadowSize,
      y+padding-shadowSize,
      circleSize-2*padding+shadowSize*2,
      circleSize-2*padding+shadowSize*2,
      new Vector4f(0.5f),
      circleSize/2-padding+shadowSize,
      shadowBlur,
      context
    )
    //circle
    context.renderer.rect(
      x+padding,
      y+padding,
      circleSize-2*padding,
      circleSize-2*padding,
      widget.circleColor,
      (circleSize-2*padding)/2,
      blur = 1,
      context
    )
  }
}

object Switch{
  val padding=2
  def apply(
             value: Boolean,
             onChange:Boolean=>Unit=null,
             backgroundColor:Vector4f=Colors.green400,
             unselectedBackgroundColor:Vector4f=Colors.gray300,
             circleColor:Vector4f=Colors.white
           ): Switch = new Switch(value,onChange,backgroundColor,unselectedBackgroundColor, circleColor)
}