using System;
using System.Text;

namespace _433_PA3
{
    public class VankinsMile
    {

        public static void findBestPath(int[,] board, int numRows, int numCols, int startRow, int startCol)
        {
            // complete this function
            // Initialize values and directions matrices
            int[,] values = new int[numRows, numCols];
            char[,] directions = new char[numRows, numCols];

            // Base case 1: Set unreachable cells to Int32.MinValue
            for (int i = 0; i < startRow; i++)
            {
                for (int j = 0; j < numCols; j++)
                {
                    values[i, j] = Int32.MinValue;
                    directions[i, j] = '*';
                }
            }
            for (int j = 0; j < startCol; j++)
            {
                for (int i = 0; i < numRows; i++)
                {
                    values[i, j] = Int32.MinValue;
                    directions[i, j] = '*';
                }
            }

            // Base case 2: Fill the starting row
            for (int j = startCol; j < numCols; j++)
            {
                if (j == startCol)
                {
                    values[startRow, j] = board[startRow, j];
                    directions[startRow, j] = 'S'; // Set starting cell direction to 'S'
                }
                else
                {
                    values[startRow, j] = values[startRow, j - 1] + board[startRow, j];
                    directions[startRow, j] = 'L';
                }
            }

            // Base case 3: Fill the starting column
            for (int i = startRow; i < numRows; i++)
            {
                if (i == startRow)
                {
                    values[i, startCol] = board[i, startCol];
                    directions[i, startCol] = 'S'; // Set starting cell direction to 'S'
                }
                else
                {
                    values[i, startCol] = values[i - 1, startCol] + board[i, startCol];
                    directions[i, startCol] = 'U';
                }
            }

            // Fill the remaining cells with the recurrence relation
            for (int i = startRow + 1; i < numRows; i++)
            {
                for (int j = startCol + 1; j < numCols; j++)
                {
                    int upValue = values[i - 1, j];
                    int leftValue = values[i, j - 1];

                    if (upValue >= leftValue)
                    {
                        values[i, j] = upValue + board[i, j];
                        directions[i, j] = 'U';
                    }
                    else
                    {
                        values[i, j] = leftValue + board[i, j];
                        directions[i, j] = 'L';
                    }
                }
            }

            // Print the solution board and the path
            pathFinder(values, directions, numRows, numCols, startRow, startCol);
        }

        private static void pathFinder(int[,] values, char[,] directions, int numRows, int numCols, int startRow, int startCol)
        {
            printBoard(values, directions, numRows, numCols);
            int max = Int32.MinValue;
            int maxR = 0;
            int maxC = 0;
            List<int[]> path = new List<int[]>();
            for (int i = startRow; i < numRows; i++)
                if (values[i, numCols - 1] > max)
                {
                    max = values[i, numCols - 1];
                    maxR = i;
                    maxC = numCols - 1;
                }
            for (int j = startCol; j < numCols; j++)
                if (values[numRows - 1, j] > max)
                {
                    max = values[numRows - 1, j];
                    maxR = numRows - 1;
                    maxC = j;
                }
            Console.WriteLine("\nMaximum gain is: " + max);
            int[] node;
            while (maxR >= startRow && maxC >= startCol)
            {
                node = new int[] { maxR, maxC };
                path.Add(node);
                if (directions[maxR, maxC] == 'L')
                    maxC--;
                else
                    maxR--;
            }
            reverse(path);

            Console.Write("Path: ");
            if (path.Count == 0)
                return;

            for (int i = 0; i < path.Count - 1; i++)
            {
                node = path[i];
                Console.Write("[{0},{1}] --> ", node[0], node[1]);
            }
            node = path[path.Count - 1];
            Console.Write("[{0},{1}]\n", node[0], node[1]);
        }

        private static void printBoard(int[,] values, char[,] directions, int numRows, int numCols)
        {
            Console.WriteLine("\nSolution (Value/Direction) Board");
            if (numRows == 0 || numCols == 0)
            {
                Console.WriteLine("[]");
                return;
            }
            for (int i = 0; i < numRows; i++)
            {
                String val = values[i, 0] == Int32.MinValue ? "-inf" : "" + values[i, 0];
                String output = "[" + val + "/" + directions[i, 0];
                for (int j = 1; j < numCols; j++)
                {
                    val = values[i, j] == Int32.MinValue ? "-inf" : "" + values[i, j];
                    output += ", " + val + "/" + directions[i, j];
                }
                output += "]";
                Console.WriteLine(output);
            }
        }

        private static void reverse(List<int[]> list)
        {
            for (int i = 0, j = list.Count - 1; i < j; i++, j--)
            {
                int[] temp = list[j];
                list[j] = list[i];
                list[i] = temp;
            }
        }
    }
}

