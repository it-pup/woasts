package io.github.umoshii.woasts.widgets

import io.github.umoshii.woasts.Woasts
import io.github.umoshii.woasts.WoastsClient
import io.github.umoshii.woasts.config.Config
import io.github.umoshii.woasts.generated.WoastsModules
import io.github.umoshii.woasts.generated.WoastsWoasts
import io.github.umoshii.woasts.helpers.McClient
import io.github.umoshii.woasts.widgets.implementation.BiomeWidget
import io.github.umoshii.woasts.widgets.implementation.DayWidget
import io.github.umoshii.woasts.widgets.implementation.FPSWidget
import io.github.umoshii.woasts.widgets.implementation.IngameTimeWidget
import io.github.umoshii.woasts.widgets.implementation.IrlTimeWidget
import io.github.umoshii.woasts.widgets.implementation.PingWidget
import io.github.umoshii.woasts.widgets.implementation.PositionWidget
import io.github.umoshii.woasts.widgets.implementation.WeatherWidget
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements
import net.minecraft.client.DeltaTracker
import net.minecraft.client.gui.GuiGraphicsExtractor

object WidgetRenderer {
    private val widgets: List<Widget<*>> = WoastsWoasts.collected

    // this is a static value, so it's justifiable to put it here like this
    private const val CONTAINER_HEIGHT: Int = 14

    @Suppress("DuplicatedCode")
    private fun render(graphics: GuiGraphicsExtractor, tickCounter: DeltaTracker) {
        //~ if < 26.2 'gui.hud.isHidden' -> 'options.hideGui'
        if (McClient.instance.gui.hud.isHidden) return

        if (!WoastsClient.config.mainSwitch) return

        val enabled = widgets.filter { it.isEnabled && it.shouldRender() }
        if (enabled.isEmpty()) return

        val lines = if (WoastsClient.config.wrap > 0) {
            enabled.chunked(WoastsClient.config.wrap)
        } else {
            enabled.chunked(enabled.size)
        }

        when(WoastsClient.config.renderCorner) {
            Config.RenderCorner.UP_LEFT -> {
                var x = WoastsClient.config.margin
                var y = WoastsClient.config.margin

                for (line in lines) {
                    for (widget in line) {
                        widget.render(graphics, tickCounter, x, y)
                        x += widget.containerWidth + WoastsClient.config.spacing
                    }

                    x = WoastsClient.config.margin
                    y += CONTAINER_HEIGHT + WoastsClient.config.wrapSpacing
                }
            }

            Config.RenderCorner.UP_RIGHT -> {
                var x = McClient.window.guiScaledWidth - WoastsClient.config.margin
                var y = WoastsClient.config.margin

                for (line in lines) {
                    for ((index, widget) in line.withIndex()) {
                        x -= if(index == 0) {
                            widget.containerWidth
                        } else {
                            widget.containerWidth + WoastsClient.config.spacing
                        }

                        widget.render(graphics, tickCounter, x, y)
                    }

                    x = McClient.window.guiScaledWidth - WoastsClient.config.margin
                    y += CONTAINER_HEIGHT + WoastsClient.config.wrapSpacing
                }
            }

            Config.RenderCorner.DOWN_LEFT -> {
                var x = WoastsClient.config.margin
                var y = McClient.window.guiScaledHeight - CONTAINER_HEIGHT - WoastsClient.config.margin

                for (line in lines) {
                    for (widget in line) {
                        widget.render(graphics, tickCounter, x, y)
                        x += widget.containerWidth + WoastsClient.config.spacing
                    }

                    x = WoastsClient.config.margin
                    y -= CONTAINER_HEIGHT + WoastsClient.config.wrapSpacing
                }
            }

            Config.RenderCorner.DOWN_RIGHT -> {
                var x = McClient.window.guiScaledWidth - WoastsClient.config.margin
                var y = McClient.window.guiScaledHeight - CONTAINER_HEIGHT - WoastsClient.config.margin

                for (line in lines) {
                    for ((index, widget) in line.withIndex()) {
                        x -= if(index == 0) {
                            widget.containerWidth
                        } else {
                            widget.containerWidth + WoastsClient.config.spacing
                        }

                        widget.render(graphics, tickCounter, x, y)
                    }

                    x = McClient.window.guiScaledWidth - WoastsClient.config.margin
                    y -= CONTAINER_HEIGHT + WoastsClient.config.wrapSpacing
                }
            }
        }
    }

    fun register() {
        HudElementRegistry.attachElementAfter(
            VanillaHudElements.SLEEP,
            Woasts.id("woasts_hud"),
            ::render
        )
    }
}
