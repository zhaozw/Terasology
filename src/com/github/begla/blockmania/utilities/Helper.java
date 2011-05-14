/*
 *  Copyright 2011 Benjamin Glatzel <benjamin.glatzel@me.com>.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */
package com.github.begla.blockmania.utilities;

import com.github.begla.blockmania.Configuration;
import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * This is a simple helper class for various tasks.
 * 
 * @author Benjamin Glatzel <benjamin.glatzel@me.com>
 */
public class Helper {

    static final float _div = 1.0f / 16.0f;
    private static long _timerTicksPerSecond = Sys.getTimerResolution();
    private static Helper _instance = null;

    /**
     * Returns the static instance of this helper class.
     *
     * @return The instance
     */
    public static Helper getInstance() {
        if (_instance == null) {
            _instance = new Helper();
        }

        return _instance;
    }

    /**
     * Calculates the texture offset for a given position within
     * the texture atlas.
     *
     * @param x X-coordinate
     * @param y Y-coordinate
     * @return The texture offset
     */
    public Vector2f calcOffsetForTextureAt(int x, int y) {
        return new Vector2f(x * _div, y * _div);
    }

    /**
     * Returns the spawning point of the player.
     * 
     * TODO: Should not determine the spawning point randomly.
     * 
     * @return The coordinates of the spawning point
     */
    public Vector3f calcPlayerOrigin() {
        return new Vector3f(Configuration.CHUNK_DIMENSIONS.x * Configuration.VIEWING_DISTANCE_IN_CHUNKS.x / 2, 127, (Configuration.CHUNK_DIMENSIONS.z * Configuration.VIEWING_DISTANCE_IN_CHUNKS.z) / 2);
    }

    /**
     * Returns the system time.
     * 
     * @return The system time
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / _timerTicksPerSecond;
    }

    /**
     * Applies Cantor's pairing function on 2D coordinates.
     *
     * @param k1 X-Coordinate
     * @param k2 Y-Coordinate
     * @return Unique 1D value
     */
    public int cantorize(int k1, int k2) {
        return ((k1 + k2) * (k1 + k2 + 1) / 2) + k2;
    }

    /**
     * 
     * @param z
     * @return
     */
    public int cantorX(int z) {
        int j = (int) Math.floor(Math.sqrt(0.25 + 2 * z) - 0.5);
        return j - cantorY(z);
    }

    /**
     * 
     * @param z
     * @return
     */
    public int cantorY(int z) {
        int j = (int) Math.floor(Math.sqrt(0.25 + 2 * z) - 0.5);
        return z - j * (j + 1) / 2;
    }
}