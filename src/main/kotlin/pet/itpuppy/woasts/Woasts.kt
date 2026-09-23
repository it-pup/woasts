package pet.itpuppy.woasts

import net.fabricmc.api.ModInitializer
import net.minecraft.resources.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Woasts : ModInitializer {
    const val MOD_ID: String = "woasts"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    fun id(path: String): Identifier = Identifier.fromNamespaceAndPath(MOD_ID, path)

    override fun onInitialize() {
        LOGGER.info("Woof!")
    }
}
