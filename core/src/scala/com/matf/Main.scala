package com.matf

import com.matf.switchsnake.App
import com.systemvi.engine.utils.Utils

import java.io.File

object Main {
  def main(args:Array[String]):Unit={
    Utils.assetsFolder=""
    new switchsnake.App().run()
  }
}
