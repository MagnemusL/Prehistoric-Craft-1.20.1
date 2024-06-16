package net.smazeee.prehistoriccraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.screen.renderer.EnergyDisplayTooltipArea;
import net.smazeee.prehistoriccraft.util.MouseUtil;

import java.util.Optional;

public class ExtractionMachineScreen extends AbstractContainerScreen<ExtractionMachineMenu> {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(PrehistoricCraft.MODID, "textures/gui/extraction_machine_gui.png");
    private EnergyDisplayTooltipArea energyInfoArea;

    public ExtractionMachineScreen(ExtractionMachineMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;

        assignEnergyInfoArea();
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderEnergyAreaTooltip(guiGraphics, mouseX, mouseY, x, y);
    }

    private void renderEnergyAreaTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y) {
        if(isMouseAboveArea(mouseX, mouseY, x, y, 44, 64, 4, 11)) {
            guiGraphics.renderTooltip(this.font, energyInfoArea.getTooltips(), Optional.empty(), mouseX - x, mouseY - y);
        }
    }

    private void assignEnergyInfoArea() {
        energyInfoArea = new EnergyDisplayTooltipArea(menu.blockEntity.getEnergyStorage());
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        renderEnergyBar(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isCrafting()) {
            guiGraphics.blit(GUI_TEXTURE, x + 64 /*64*/, y + 35 /*36*/, 176 /*176*/, 33 /*33*/, 49 /*49*/, (int) menu.getScaledProgress());
        }
    }

    private void renderEnergyBar(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(GUI_TEXTURE, x + 44 /*44*/, y + 64 /*64*/, 176 /*176*/, 71 /*71*/, 4 /*4*/, 11 - menu.getEnergyBarAmount() * menu.getEnergyBarAmount());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private boolean isMouseAboveArea(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, width, height);
    }
}
