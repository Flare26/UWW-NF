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
                if (encodingToCharMapping.ContainsKey(encode.ToArray()))
                {
                    char c = (char)encodingToCharMapping[encode.ToArray()];
                    decodedMsg += c;
                    //encode.Clear();
                }
            }

            return decodedMsg; // build into string value and return
        }

    }
}
