package thyve.test.service;

import java.util.List;

public class CustomEncoder {

    public void batchEncode(List<Byte> resultList, byte[] buffer, int useTrivialImplementation) {
        if (useTrivialImplementation == 0)
            simpleEncoding(resultList, buffer);
        if (useTrivialImplementation == 1)
            optimizedEncoding(resultList, buffer);
    }

    private void simpleEncoding(List<Byte> resultList, byte[] buffer) {
        for (byte theByte : buffer) {
            addSimpleByteTouple(resultList, theByte);
        }

    }

    //todo
    private void optimizedEncoding(List<Byte> resultList, byte[] buffer) {
        for (int i = 0; i <buffer.length; i++) {
            if (i==0)
                addSimpleByteTouple(resultList, buffer[i]);
//            else
        }
    }

    private void addSimpleByteTouple(List<Byte> resultList, byte theByte) {
        resultList.add(Byte.valueOf((byte) 0));
        resultList.add(theByte);
    }
}
