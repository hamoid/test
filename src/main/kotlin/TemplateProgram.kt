import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extensions.SingleScreenshot

fun main() = application {
    configure {
        width = 768
        height = 576
    }

    program {
        // Saves the first rendered frame and then quits the program.
        extend(SingleScreenshot()) {
            outputFile = "screenshot.png"
        }

        extend {
            drawer.clear(ColorRGBa.BLACK)
            drawer.fill = ColorRGBa.PINK
            drawer.circle(width / 2.0, height / 2.0, 140.0)
        }
    }
}
