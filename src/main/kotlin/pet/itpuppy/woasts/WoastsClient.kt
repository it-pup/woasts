package pet.itpuppy.woasts

import pet.itpuppy.woasts.commands.ConfigCommand
import pet.itpuppy.woasts.config.Config
import pet.itpuppy.woasts.widgets.WidgetRenderer
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer
import net.fabricmc.api.ClientModInitializer

object WoastsClient : ClientModInitializer {
    lateinit var config: Config

    override fun onInitializeClient() {
        AutoConfig.register(Config::class.java, ::GsonConfigSerializer)
        config = AutoConfig.getConfigHolder(Config::class.java).config

        WidgetRenderer.register()
        ConfigCommand.register()
    }
}
