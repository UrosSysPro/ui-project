package com.matf.paint

import com.matf.ui.runApp
import com.systemvi.engine.utils.Utils

object Main {
  def main(args: Array[String]): Unit = {
    Utils.assetsFolder="../"
    runApp(
      "Paint",
      new Paint()
    )
  }
}
