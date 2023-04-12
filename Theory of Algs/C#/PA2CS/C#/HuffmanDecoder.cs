using System;
using System.Collections;
namespace _PA2
{
    public class HuffmanDecoder
    {
        public static string decode(string encodedMsg, Hashtable encodingToCharMapping)
        {// complete this method
            string decodedMsg = "";
            string encode = "";
            int n = encodedMsg.Length;

            for (int i = 0; i < n; i++)
            {
                encode += encodedMsg[i];
                if (encodingToCharMapping.ContainsKey(encode))
                {
                    char c = (char) encodingToCharMapping[encode];
                    decodedMsg += c;
                    encode = "";
                }
            }

            return decodedMsg; // build into string value and return
        }

    }
}
