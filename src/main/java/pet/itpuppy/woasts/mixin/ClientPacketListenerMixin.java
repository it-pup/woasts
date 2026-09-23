package pet.itpuppy.woasts.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.client.multiplayer.ClientPacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// tricks the game into thinking that the network overlay in F3 menu is always visible
// part of ping widget
@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @WrapOperation(
        method = "tick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/components/DebugScreenOverlay;showNetworkCharts()Z"
        )
    )
    private boolean onShowNetworkCharts(DebugScreenOverlay instance, Operation<Boolean> original) {
        return true;
    }
}
