/*
 * Copyright (c) 2016 Helmut Neemann
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digital.draw.shapes;

import de.neemann.digital.core.Observer;
import de.neemann.digital.core.element.ElementAttributes;
import de.neemann.digital.core.element.Keys;
import de.neemann.digital.core.element.PinDescriptions;
import de.neemann.digital.draw.elements.IOState;
import de.neemann.digital.draw.elements.Pins;
import de.neemann.digital.draw.graphics.*;
import de.neemann.digital.lang.Lang;

import static de.neemann.digital.draw.shapes.GenericShape.SIZE;

/**
 * Simple text
 */
public class RectShape implements Shape, DecoratingShape {
    private final String label;
    private final int width;
    private final int height;

    /**
     * Create a new instance
     *
     * @param attr    attributes
     * @param inputs  the inputs
     * @param outputs the outputs
     */
    public RectShape(ElementAttributes attr, PinDescriptions inputs, PinDescriptions outputs) {
        final String l = attr.get(Keys.LABEL);
        if (l.isEmpty())
            label = Lang.get("elem_Text");
        else if (l.equals("-"))
            label = "";
        else
            label = Lang.evalMultilingualContent(l);

        width = attr.get(Keys.RECT_WIDTH);
        height = attr.get(Keys.RECT_HEIGHT);
    }

    @Override
    public Pins getPins() {
        return new Pins();
    }

    @Override
    public Interactor applyStateMonitor(IOState ioState, Observer guiObserver) {
        return null;
    }

    @Override
    public void drawTo(Graphic graphic, Style highLight) {
        Vector pos = new Vector(0, -2);
        Style style = Style.NORMAL;
        if (!label.isEmpty())
            graphic.drawText(pos, pos.add(1, 0), label, Orientation.LEFTBOTTOM, style);

        graphic.drawPolygon(new Polygon(true)
                .add(0, 0)
                .add(width * SIZE, 0)
                .add(width * SIZE, height * SIZE)
                .add(0, height * SIZE), Style.DASH);
    }
}