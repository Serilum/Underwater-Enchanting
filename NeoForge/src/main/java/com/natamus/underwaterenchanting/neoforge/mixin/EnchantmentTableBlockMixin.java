package com.natamus.underwaterenchanting.neoforge.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EnchantmentTableBlock.class, priority = 1001)
public abstract class EnchantmentTableBlockMixin {
	@Inject(method = "isValidBookShelf(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Z", at = @At(value = "HEAD"), cancellable = true)
	private static void isValidBookShelf(Level level, BlockPos blockPos, BlockPos blockPos2, CallbackInfoReturnable<Boolean> cir) {
		BlockPos offsetPos = blockPos.offset(blockPos2.getX() / 2, blockPos2.getY(), blockPos2.getZ() / 2);

		cir.setReturnValue(level.getBlockState(blockPos.offset(blockPos2)).is(Blocks.BOOKSHELF) && (level.isEmptyBlock(offsetPos) || level.getBlockState(offsetPos).getBlock().equals(Blocks.WATER)));
	}
}
