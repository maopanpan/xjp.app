package com.xjp.serial.fst;

import com.xjp.serial.Serializer;
import de.ruedigermoeller.serialization.FSTObjectInput;
import de.ruedigermoeller.serialization.FSTObjectOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @Author: maopanpan
 * @Description: FST序列化、FST反序列化
 * @Date: 2017/10/12.
 **/
public class FSTSerializableImpl implements Serializer {
    public String getName() {
        return "fst";
    }

    public byte[] serialize(Object... objects) throws Exception {
        ByteArrayOutputStream out = null;
        FSTObjectOutput fout = null;
        try {
            out = new ByteArrayOutputStream();
            fout = new FSTObjectOutput(out);
            fout.writeObject(objects[0]);
            return out.toByteArray();
        } finally {
            if (fout != null) {
                close(fout);
                close(out);
            }
        }
    }

    public Object deserialize(Object... bytes) throws Exception {
        if (bytes == null)
            return null;
        FSTObjectInput in = null;
        try {
            in = new FSTObjectInput(new ByteArrayInputStream((byte[]) bytes[0]));
            return in.readObject();
        } finally {
            if (in != null)
                close(in);
        }
    }
}
