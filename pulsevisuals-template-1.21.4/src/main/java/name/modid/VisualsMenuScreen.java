package name.modid;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class VisualsMenuScreen extends Screen {
    public static boolean pulseVisualEnabled = false;
    public static boolean chinaHatEnabled = false;
    public static boolean blockEspEnabled = false;

    public VisualsMenuScreen() {
        super(Text.of("Visuals Menu"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);

        int x = this.width / 2 - 75; 
        int y = this.height / 2 - 100;
        int width = 150;
        int height = 180;

        // Рисуем темную панель меню
        context.fill(x, y, x + width, y + height, 0xDD111116);

        // Заголовок Visuals
        context.drawText(this.textRenderer, "Visuals", x + (width / 2) - (this.textRenderer.getWidth("Visuals") / 2), y + 12, 0xFFFFFFFF, false);
        context.fill(x + 10, y + 26, x + width - 10, y + 27, 0x33FFFFFF);

        // Кнопки переключения функций
        drawMenuButton(context, "Pulse Visual", x + 15, y + 40, pulseVisualEnabled, mouseX, mouseY);
        drawMenuButton(context, "China Hat", x + 15, y + 65, chinaHatEnabled, mouseX, mouseY);
        drawMenuButton(context, "Block ESP", x + 15, y + 90, blockEspEnabled, mouseX, mouseY);

        super.render(context, mouseX, mouseY, delta);
    }

    private void drawMenuButton(DrawContext context, String text, int bx, int by, boolean enabled, int mouseX, int mouseY) {
        int textColor = enabled ? 0xFF55FFFF : 0xFF999999;
        if (mouseX >= bx && mouseX <= bx + 120 && mouseY >= by && mouseY <= by + 10) {
            textColor = 0xFFFFFFFF; // Подсветка белым при наведении мышки
        }
        context.drawText(this.textRenderer, text, bx, by, textColor, false);
        
        String status = enabled ? "[ON]" : "[OFF]";
        int statusColor = enabled ? 0xFF00FF00 : 0xFFFF0000;
        context.drawText(this.textRenderer, status, bx + 95, by, statusColor, false);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int x = this.width / 2 - 75;
        int y = this.height / 2 - 100;

        if (mouseX >= x + 15 && mouseX <= x + 135 && mouseY >= y + 40 && mouseY <= y + 50) {
            pulseVisualEnabled = !pulseVisualEnabled;
            return true;
        }
        if (mouseX >= x + 15 && mouseX <= x + 135 && mouseY >= y + 65 && mouseY <= y + 75) {
            chinaHatEnabled = !chinaHatEnabled;
            return true;
        }
        if (mouseX >= x + 15 && mouseX <= x + 135 && mouseY >= y + 90 && mouseY <= y + 100) {
            blockEspEnabled = !blockEspEnabled;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
