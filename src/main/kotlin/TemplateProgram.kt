import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.extensions.SingleScreenshot

fun main() {
    try {
        application {
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
    } catch (e: Throwable) {
        // TEMPORARY DEBUG: OPENRNDR doesn't include SDL_GetError() in "failed to create primary window".
        // Reflection, because lwjgl-sdl is only on the runtime classpath.
        val sdlError = runCatching {
            Class.forName("org.lwjgl.sdl.SDLError").getMethod("SDL_GetError").invoke(null)
        }.getOrElse { "unavailable (${it.javaClass.simpleName}: ${it.message})" }
        System.err.println("SDL_GetError(): $sdlError")
        throw e
    }
}
