//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.file;

import commons.file.AbstractFile;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonFile extends AbstractFile<Map<String, Object>> {
    public JsonFile(Path file) {
        super(file);
    }

    public Map<String, Object> content() throws IOException {
        try {
            TextFile source = new TextFile(this.path());
            Map<String, Object> json = (new JSONObject(source.content())).toMap();
            return json;
        } catch (JSONException var3) {
            JSONException e = var3;
            throw new InvalidFileContentException(String.format("Invalid json: %s", e.getMessage()), e);
        }
    }

    public void write(Map<String, Object> object) throws IOException {
        String json = (new JSONObject(object)).toString();
        TextFile destination = new TextFile(this.path());
        destination.write(json);
    }
}
