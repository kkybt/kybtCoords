package dev.kybt.kcoords.gui;

import dev.kybt.kcoords.KybtCoords;
import dev.kybt.kcoords.Utils;
import dev.kybt.kcoords.events.Subscriber;
import dev.kybt.kcoords.render.SurfaceBuilder;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.config.GuiSlider;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ConfigGUI extends GuiScreen {
    private final Subscriber subscribe = new Subscriber();
    private final SurfaceBuilder surfaceBuilder = SurfaceBuilder.getInstance();
//    private GuiButton buttonCoordsEnabled;
    private GuiButton buttonShowFPS;
    private GuiButton buttonColoredBiomes;
    private GuiButton buttonShowC;
    private GuiButton buttonShowBiomes;

    private GuiSlider sliderScale;

//    private ColorButton buttonKeyColor;
//    private ColorButton buttonTextColor;
//    private ColorButton buttonBackgroundColor;

    private GuiButton buttonLabelColor;
    private GuiButton buttonTextColor;
    private GuiButton buttonBackgroundColor;
//    private GuiButton buttonColors;

//    private GuiTextField fieldKeyColor;
//    private GuiTextField fieldTextColor;
//    private GuiSlider buttonScale;

//    private GuiSlider sliderMarkerColor;
//    private GuiSlider sliderTextColor;

    @Override
    public void initGui() {
        buttonShowFPS = new GuiButton(0, (width / 2 - 60), (height / 2 - 62), 150, 20, "FPS: " + (KybtCoords.showFPS ? "Enabled" : "Disabled"));
        buttonList.add(buttonShowFPS);

        buttonColoredBiomes = new GuiButton(1, (width / 2 - 60), (height / 2 - 40), 150, 20, "Biome colors: " + (KybtCoords.coloredBiomes ? "Enabled" : "Disabled"));
        buttonList.add(buttonColoredBiomes);

        buttonShowC = new GuiButton(2, (width / 2 - 60), (height / 2 - 18), 150, 20, "C counter: " + (KybtCoords.showC ? "Enabled" : "Disabled"));
        buttonList.add(buttonShowC);

        buttonShowBiomes = new GuiButton(3, (width / 2 - 60), (height / 2 + 4), 150, 20, "Biome: " + (KybtCoords.showBiomes ? "Enabled" : "Disabled"));
        buttonList.add(buttonShowBiomes);

        sliderScale = new GuiSlider(4, (width / 2 - 60), (height / 2 + 26), 150, 20,
                "Scale: ", "", 0.50D, 1.50D, KybtCoords.scale, true, true);
        buttonList.add(sliderScale);
        sliderScale.precision = 2;

        buttonLabelColor = new GuiButton(5, (width / 2 - 60), (height / 2 + 48), 150, 20,
                "Label color");
        surfaceBuilder.drawOutlinedRectFilled((width / 2 - 60) + 2, (height / 2 + 48), 20, 20, Utils.WHITE,
                KybtCoords.labelColor, 1);
        buttonList.add(buttonLabelColor);

        buttonTextColor = new GuiButton(6, (width / 2 - 60), (height / 2 + 69), 150, 20,
                "Text color");
        surfaceBuilder.drawOutlinedRectFilled((width / 2 - 60) + 2, (height / 2 + 69), 20, 20, Utils.WHITE,
                KybtCoords.textColor, 1);
        buttonList.add(buttonTextColor);

        buttonBackgroundColor = new GuiButton(7, (width / 2 - 60), (height / 2 + 90), 150, 20,
                "Background color");
        surfaceBuilder.drawOutlinedRectFilled((width / 2 - 60) + 2, (height / 2 + 90), 20, 20, Utils.WHITE,
                KybtCoords.backgroundColor, 1);
        buttonList.add(buttonBackgroundColor);


//        surfaceHelper.drawText("Key color: ", (width / 2 - 60) - surfaceHelper.getFontWidth("Key color: "), (height / 2 + 48), Utils.WHITE, 1.0, true);
//        buttonKeyColor = new ColorButton(5, (width / 2 - 60), (height / 2 + 48), "", 0);
//        buttonList.add(buttonKeyColor);
//
//        surfaceHelper.drawText("Text color: ", (width / 2 - 60) - surfaceHelper.getFontWidth("Text color: "), (height / 2 + 69), Utils.WHITE, 1.0, true);
//        buttonTextColor = new ColorButton(6, (width / 2 - 60), (height / 2 + 69), "", 1);
//        buttonList.add(buttonTextColor);
//
//        surfaceHelper.drawText("Background color: ", (width / 2 - 60) - surfaceHelper.getFontWidth("Background color: "), (height / 2 + 90), Utils.WHITE, 1.0, true);
//        buttonBackgroundColor = new ColorButton(7, (width / 2 - 60), (height / 2 + 90), "", 2);
//        buttonList.add(buttonBackgroundColor);
//        fieldKeyColor = new GuiTextField(4, minecraft.fontRendererObj, (width / 2 - 70), (height / 2 + 26), 100, 10);
//        labelList.add(fieldKeyColor);
    }

    public void display() {
        MinecraftForge.EVENT_BUS.register(this);
//        minecraft.displayGuiScreen(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        MinecraftForge.EVENT_BUS.unregister(this);
        Utils.getMinecraft().displayGuiScreen(this);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        subscribe.renderData();
        subscribe.restrictBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void updateScreen() {
        super.updateScreen();
        KybtCoords.scale = sliderScale.getValue();
    }

    protected void actionPerformed(GuiButton button) {
        switch(button.id) {
            case 0:
                buttonShowFPS.displayString = ("FPS: " + ((KybtCoords.showFPS = !KybtCoords.showFPS) ? "Enabled" : "Disabled"));
                break;
            case 1:
                buttonColoredBiomes.displayString = ("Biome colors: " + ((KybtCoords.coloredBiomes = !KybtCoords.coloredBiomes) ? "Enabled" : "Disabled"));
                break;
            case 2:
                buttonShowC.displayString = ("C counter: " + ((KybtCoords.showC = !KybtCoords.showC) ? "Enabled" : "Disabled"));
                break;
            case 3:
                buttonShowBiomes.displayString = ("Biome: " + ((KybtCoords.showBiomes = !KybtCoords.showBiomes) ? "Enabled" : "Disabled"));
                break;
            case 4:
//                sliderScale.displayString = ("Scale: " + (KybtCoords.scale)); // (KybtCoords.scale = sliderScale.sliderValue)
                break;
            case 5:
//                minecraft.displayGuiScreen(null);
                new ColorGUI(0).display();
                break;
            case 6:
//                minecraft.displayGuiScreen(null);
                new ColorGUI(1).display();
                break;
            case 7:
//                minecraft.displayGuiScreen(null);
                new ColorGUI(2).display();
                break;
        }
    }

    @Override
    public void func_183500_a(int width, int height) {  // setGuiSize();
        super.func_183500_a(width, height);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Utils.saveSettings();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
