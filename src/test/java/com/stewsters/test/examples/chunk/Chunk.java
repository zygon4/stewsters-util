package com.stewsters.test.examples.chunk;

import com.stewsters.test.examples.ExampleCellType;
import com.stewsters.util.pathing.twoDimention.shared.TileBasedMap2d;

import java.util.ArrayList;

public class Chunk implements TileBasedMap2d {
    private static final int uncalculatedRegion = -1;
    private static final int blockedRegion = -2;
    public static final int xSize = 16;
    public static final int ySize = 16;

    public final int xOffset;
    public final int yOffset;

    public ExampleCellType[][] ground;

    public int[][] regionIds;
    public ArrayList<OverworldPathNode> overworldPathNodes;


    public Chunk(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;

        ground = new ExampleCellType[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                ground[x][y] = new ExampleCellType('.', false);
            }
        }

        regionIds = new int[xSize][ySize];
        overworldPathNodes = new ArrayList<>();
    }


    public void recalculate() {
        //Reset
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (ground[x][y].isBlocking())
                    regionIds[x][y] = blockedRegion;
                else
                    regionIds[x][y] = uncalculatedRegion;
            }
        }

        int i = 0;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (regionIds[x][y] == uncalculatedRegion) {
                    floodFill(x, y, uncalculatedRegion, i++);
                }
            }
        }
    }

    private void floodFill(int x, int y, int target, int replacement) {

        if (target == replacement)
            return;
        if (target != regionIds[x][y])
            return;

        regionIds[x][y] = replacement;

        if (x < xSize - 1)
            floodFill(x + 1, y, target, replacement);
        if (x > 0)
            floodFill(x - 1, y, target, replacement);
        if (y < ySize - 1)
            floodFill(x, y + 1, target, replacement);
        if (y > 0)
            floodFill(x, y - 1, target, replacement);
    }

    @Override
    public int getXSize() {
        return xSize;
    }

    @Override
    public int getYSize() {
        return ySize;
    }

    @Override
    public void pathFinderVisited(int x, int y) {

    }

    @Override
    public String toString() {
        return xOffset + ", " + yOffset;
    }
}
