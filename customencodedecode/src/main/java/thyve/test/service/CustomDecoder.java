package thyve.test.service;

import java.util.List;

public class CustomDecoder {

    public void batchDecode(List<Byte> resultList, byte[] buffer) {
        byte[] byteTouple = new byte[2];
        for (int i = 0; i < buffer.length; i=i+2) {
            System.arraycopy(buffer, i , byteTouple, 0,2);
            decode(resultList, byteTouple);
        }
    }

    private void decode(List<Byte> resultList, byte[] byteTouple) {
       if (byteTouple[0] == 0){
           resultList.add(byteTouple[1]);
       }else {
            if (byteTouple[0] < byteTouple[1] || (byteTouple[1]<0) || (byteTouple[0]<0))
                resultList.add((byte) 63);// adding 3f for can not decoding
            else{
                if (resultList.size() < byteTouple[0])
                    resultList.add((byte) 63);// adding 3f for can not decoding
                else {
                    List<Byte> pBytes = resultList.subList(resultList.size() - byteTouple[1], resultList.size());// get last p element of list
                    List<Byte> qBytes = pBytes.subList(0, byteTouple[1]);
                    resultList.addAll(qBytes);
                }
            }
       }

    }
}
