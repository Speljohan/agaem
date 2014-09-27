package org.gaem.core.util;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.ETC1TextureData;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.utils.Array;

public class YDownTextureLoader extends AsynchronousAssetLoader<Texture, YDownTextureLoader.TextureParameter> {

    static public class TextureLoaderInfo {
        String filename;
        TextureData data;
        Texture texture;
    }

    TextureLoaderInfo info = new TextureLoaderInfo();

    public YDownTextureLoader (FileHandleResolver resolver) {
        super(resolver);
    }

    public void loadAsync (AssetManager manager, String fileName, FileHandle file, TextureParameter parameter) {
        info.filename = fileName;
        if (parameter == null || parameter.textureData == null) {
            Pixmap pixmap = null;
            Format format = null;
            boolean genMipMaps = false;
            info.texture = null;

            if (parameter != null) {
                format = parameter.format;
                genMipMaps = parameter.genMipMaps;
                info.texture = parameter.texture;
            }

            if (!fileName.contains(".etc1")) {
                if (fileName.contains(".cim"))
                    pixmap = PixmapIO.readCIM(file);
                else
                    pixmap = new Pixmap(file);

                pixmap = flipPixmap(pixmap);
                info.data = new FileTextureData(file, pixmap, format, genMipMaps);
            } else {
                info.data = new ETC1TextureData(file, genMipMaps);
            }
        } else {
            info.data = parameter.textureData;
            info.texture = parameter.texture;
        }
        if (!info.data.isPrepared()) info.data.prepare();
    }

    @Override
    public Texture loadSync (AssetManager manager, String fileName, FileHandle file, TextureParameter parameter) {
        if (info == null) return null;
        Texture texture = info.texture;
        if (texture != null) {
            texture.load(info.data);
        } else {
            texture = new Texture(info.data);
        }
        if (parameter != null) {
            texture.setFilter(parameter.minFilter, parameter.magFilter);
            texture.setWrap(parameter.wrapU, parameter.wrapV);
        }
        return texture;
    }

    public Pixmap flipPixmap(Pixmap src) {
        final int width = src.getWidth();
        final int height = src.getHeight();
        Pixmap flipped = new Pixmap(width, height, src.getFormat());

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                flipped.drawPixel(x, y, src.getPixel(x, height - y - 1));
            }
        }
        return flipped;
    }

    @Override
    public Array<AssetDescriptor> getDependencies (String fileName, FileHandle file, TextureParameter parameter) {
        return null;
    }

    static public class TextureParameter extends AssetLoaderParameters<Texture> {
        /** the format of the final Texture. Uses the source images format if null **/
        public Format format = null;
        /** whether to generate mipmaps **/
        public boolean genMipMaps = false;
        /** The texture to put the {@link TextureData} in, optional. **/
        public Texture texture = null;
        /** TextureData for textures created on the fly, optional. When set, all format and genMipMaps are ignored */
        public TextureData textureData = null;
        public TextureFilter minFilter = TextureFilter.Nearest;
        public TextureFilter magFilter = TextureFilter.Nearest;
        public TextureWrap wrapU = TextureWrap.ClampToEdge;
        public TextureWrap wrapV = TextureWrap.ClampToEdge;
    }
}
