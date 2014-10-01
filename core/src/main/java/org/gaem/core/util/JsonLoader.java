package org.gaem.core.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by Johan on 2014-10-01.
 */
public class JsonLoader extends AsynchronousAssetLoader<JsonValue, JsonLoader.JsonParameters> {

    public JsonLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, JsonParameters parameter) {

    }

    @Override
    public JsonValue loadSync(AssetManager manager, String fileName, FileHandle file, JsonParameters parameter) {
        JsonReader reader = new JsonReader();
        return reader.parse(file);
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, JsonParameters parameter) {
        return new Array<AssetDescriptor>();
    }

    public static class JsonParameters extends AssetLoaderParameters<JsonValue> {

    }
}
