package name.modid;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class VisualsMenuScreen extends Screen {
    
    private boolean draggingHud = false;
    private double dragOffsetX = 0;
    private double dragOffsetY = 0;

    public VisualsMenuScreen() {
        super(Component.literal("ClickGUI"));
    }

    @Override
    protected void init() {
        super.init();
        
        int panelWidth = 100;
        int panelGap = 10;
        int totalWidth = (panelWidth * 5) + (panelGap * 4);
        int startX = (this.width - totalWidth) / 2;
        int startY = 40;

        // КОЛОНКА MOVEMENT (Индекс 1)
        int movementX = startX + (1 * (panelWidth + panelGap));
        
        this.addRenderableWidget(Button.builder(
            Component.literal(Modules.autoSprint ? "AutoSprint: ON" : "AutoSprint: OFF"), 
            button -> {
                Modules.autoSprint = !Modules.autoSprint;
                button.setMessage(Component.literal(Modules.autoSprint ? "AutoSprint: ON" : "AutoSprint: OFF"));
            }
        ).bounds(movementX + 5, startY + 25, 90, 18).build());

        this.addRenderableWidget(Button.builder(
            Component.literal(Modules.jumpCircles ? "JumpCircles: ON" : "JumpCircles: OFF"), 
            button -> {
                Modules.jumpCircles = !Modules.jumpCircles;
                button.setMessage(Component.literal(Modules.jumpCircles ? "JumpCircles: ON" : "JumpCircles: OFF"));
            }
        ).bounds(movementX + 5, startY + 46, 90, 18).build());

        // КОЛОНКА VISUALS (Индекс 2)
        int visualsX = startX + (2 * (panelWidth + panelGap));
        
        this.addRenderableWidget(Button.builder(
            Component.literal(Modules.esp ? "ESP: ON" : "ESP: OFF"), 
            button -> {
                Modules.esp = !Modules.esp;
                button.setMessage(Component.literal(Modules.esp ? "ESP: ON" : "ESP: OFF"));
            }
        ).bounds(visualsX + 5, startY + 25, 90, 18).build());
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            Minecraft client = Minecraft.getInstance();
            String serverType = client.isSingleplayer() ? "Singleplayer" : "Multiplayer";
            // ИСПРАВЛЕНО: заменено на client.getFps()
            String watermarkText = "pulse visuals  |  " + client.getUser().getName() + "  |  " + client.getFps() + " fps  |  " + serverType;
            int hudWidth = client.font.width(watermarkText) + 10;
            
            if (mouseX >= Modules.hudX && mouseX <= Modules.hudX + hudWidth &&
                mouseY >= Modules.hudY && mouseY <= Modules.hudY + 17) {
                this.draggingHud = true;
                this.dragOffsetX = mouseX - Modules.hudX;
                this.dragOffsetY = mouseY - Modules.hudY;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) this.draggingHud = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.draggingHud) {
            Modules.hudX = (int) (mouseX - this.dragOffsetX);
            Modules.hudY = (int) (mouseY - this.dragOffsetY);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        
        String[] categories = {"Combat", "Movement", "Visuals", "Player", "Misc"};
        int panelWidth = 100;
        int panelGap = 10;
        int totalWidth = (panelWidth * 5) + (panelGap * 4);
        int startX = (this.width - totalWidth) / 2;
        int startY = 40;
        int panelHeight = 180;

        for (int i = 0; i < categories.length; i++) {
            int pX = startX + (i * (panelWidth + panelGap));
            guiGraphics.fill(pX, startY, pX + panelWidth, startY + panelHeight, 0xD512131C);
            guiGraphics.fill(pX, startY + 18, pX + panelWidth, startY + 19, 0xFF2A2C3D);
            guiGraphics.drawCenteredString(this.font, categories[i], pX + (panelWidth / 2), startY + 5, 0xFFFFFFFF);
        }
    }
}
