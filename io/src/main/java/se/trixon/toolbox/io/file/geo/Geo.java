/* 
 * Copyright 2019 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.toolbox.io.file.geo;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import se.trixon.toolbox.io.file.CoordinateFile;

/**
 *
 * @author Patrik Karlsson
 */
public class Geo extends CoordinateFile {

    private List<GeoAttribute> mAttributes = new LinkedList<>();
    private GeoHeader mHeader = new GeoHeader();
    private List<GeoLine> mLines = new LinkedList<>();
    private List<GeoPoint> mPoints = new LinkedList<>();

    public Geo() {
        GeoPoint.setLineEnding("\r\n");
    }

    public static FileNameExtensionFilter getFileNameExtensionFilter() {
        return new FileNameExtensionFilter("*.geo", "geo");
    }

    public void addPoint(GeoPoint geoPoint) {
        if (mPoints.isEmpty()) {
            mPath2D.moveTo(geoPoint.getY(), geoPoint.getX());
        } else {
            mPath2D.lineTo(geoPoint.getY(), geoPoint.getX());
        }

        mPoints.add(geoPoint);
    }

    public List<GeoAttribute> getAttributes() {
        return mAttributes;
    }

    public GeoHeader getHeader() {
        return mHeader;
    }

    public List<GeoLine> getLines() {
        return mLines;
    }

    public List<GeoPoint> getPoints() {
        return mPoints;
    }

    @Override
    public boolean isValid(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAttributes(List<GeoAttribute> attributes) {
        mAttributes = attributes;
    }

    public void setHeader(GeoHeader header) {
        mHeader = header;
    }

    @Override
    public void setLineEnding(String lineEnding) {
        super.setLineEnding(lineEnding);
        GeoPoint.setLineEnding(lineEnding);
    }

    public void setLines(List<GeoLine> lines) {
        mLines = lines;
    }

    public void setPoints(List<GeoPoint> points) {
        mPoints = points;
    }

    public void write(GeoPoint geoPoint) throws IOException {
        mWriter.write(geoPoint.toString());
    }

    public void write(File file) throws IOException {
        openWriter(file);

        mWriter.write(mHeader.toString());
        writePointList();
        writeLineList();
        writeAttributeList();

        closeWriter();
    }

    private void writeAttributeList() throws IOException {
        mWriter.write("AttributeList");
        mWriter.write(mLineEnding);
        if (!mPoints.isEmpty()) {
            mWriter.write("begin");
            mWriter.write(mLineEnding);
            for (GeoPoint geoPoint : mPoints) {
                mWriter.write(geoPoint.toString());
            }
            mWriter.write("end");
            mWriter.write(mLineEnding);
        }
    }

    private void writePointList() throws IOException {
        mWriter.write("PointList");
        mWriter.write(mLineEnding);
        if (!mPoints.isEmpty()) {
            mWriter.write("begin");
            mWriter.write(mLineEnding);
            for (GeoPoint geoPoint : mPoints) {
                mWriter.write(geoPoint.toString());
            }
            mWriter.write("end");
            mWriter.write(mLineEnding);
        }
    }

    private void writeLineList() throws IOException {
        mWriter.write("LineList");
        mWriter.write(mLineEnding);
        if (!mLines.isEmpty()) {
            mWriter.write("begin");
            mWriter.write(mLineEnding);
            for (GeoLine geoLine : mLines) {
                mWriter.write(geoLine.toString());
            }
            mWriter.write("end");
            mWriter.write(mLineEnding);
        }
    }
}
