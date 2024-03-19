package com.matf.ui.widgets

import com.matf.ui.utils.context.{BuildContext, DrawContext}
import com.matf.ui.utils.data.BoxDecoration
import com.matf.ui.{Widget, WidgetRenderer}
import org.joml.Vector4f


class Container(child:Widget, val color:Vector4f, decoration: BoxDecoration) extends StatelessWidget{
  override def build(context:BuildContext): Widget = child

  override def draw(context:DrawContext): Unit = {
    if(decoration!=null){
      decoration match {
        case BoxDecoration(color, borderRadius, _, boxShadow)=>
          boxShadow.foreach{
            boxShadow=>context.renderer.rect(
              position.x+boxShadow.offset.x-boxShadow.size/2f,
              position.y+boxShadow.offset.y-boxShadow.size/2,
              size.x+boxShadow.size,
              size.y+boxShadow.size,
              boxShadow.color,
              borderRadius,
              boxShadow.blur
            )
          }
          context.renderer.rect(position.x,position.y,size.x,size.y,color,borderRadius,1)
      }
    }else if(color!=null){
      context.renderer.rect(position.x,position.y,size.x,size.y,color)
    }
    super.draw(context)
  }
}

object Container{
  def apply(child: Widget=null,color:Vector4f=null,decoration: BoxDecoration=null): Container = new Container(child,color,decoration)
}

