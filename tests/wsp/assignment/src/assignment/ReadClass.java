package assignment;

import java.io.IOException;

public class ReadClass {
	
	/******************************************************************
	 * STUBS
	 ******************************************************************/
	
	ObjectsClass Objects = new ObjectsClass();
	byte[] ExpectedRead = new byte[10];
	int ExpectedReadIndex;
	int ExpectedReadLen;
	
	/* Stub class Objects */
	private class ObjectsClass {
		
		public void checkFromIndexSize(int fromIndex, int size, int length) {
			// TODO Auto-generated method stub
		}
	}
	
	public void setExpectedRead(byte a[], int len)
	{
		for(int i = 0; i < len; ++i)
		{
			ExpectedRead[i] = a[i];
		}
		
		ExpectedReadLen = len;
		ExpectedReadIndex = 0;
	}

    /* Stub function read */
	private int read() throws IOException {
		if(ExpectedReadLen > 0)
		{
			ExpectedReadLen--;
			return ExpectedRead[ExpectedReadIndex++];
		}
		else
		{
			return -1;
		}
	}

	/******************************************************************
	 * FUNCTION 5:
	 * https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/io/InputStream.Java
	 * Test Type: Statement Coverage
	 ******************************************************************/
    public int read(byte b[], int off, int len) throws IOException {
        Objects.checkFromIndexSize(off, len, b.length);
        if (len == 0) {
            return 0;
        }

        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte)c;

        int i = 1;
        try {
            for (; i < len ; i++) {
                c = read();
                if (c == -1) {
                    break;
                }
                b[off + i] = (byte)c;
            }
        } catch (IOException ee) {
        }
        return i;
    }
}
