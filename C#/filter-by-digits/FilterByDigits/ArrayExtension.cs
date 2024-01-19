using System;

namespace FilterTask
{
    public static class ArrayExtension
    {
        /// <summary>
        /// Returns new array of elements that contain expected digit from source array.
        /// </summary>
        /// <param name="source">Source array.</param>
        /// <param name="digit">Expected digit.</param>
        /// <returns>Array of elements that contain expected digit.</returns>
        /// <exception cref="ArgumentNullException">Thrown when array is null.</exception>
        /// <exception cref="ArgumentException">Thrown when array is empty.</exception>
        /// <exception cref="ArgumentOutOfRangeException">Thrown when digit value is out of range (0..9).</exception>
        /// <example>
        /// {1, 2, 3, 4, 5, 6, 7, 68, 69, 70, 15, 17}  => { 7, 70, 17 } for digit = 7.
        /// </example>
        public static int[] FilterByDigit(int[]? source, int digit)
        {
            if (source == null)
            {
                throw new ArgumentNullException(nameof(source));
            }
            else if (source.Length == 0)
            {
                throw new ArgumentException(nameof(source.Length));
            }
            else if (digit < 0 || digit > 9)
            {
                throw new ArgumentOutOfRangeException(nameof(digit));
            }

            List<int> filteredNumbers = new List<int>();

            foreach (int num in source)
            {
                int temp = Math.Abs(num);
                bool containsDigit = false;

                if (temp == 0 && digit == 0)
                {
                    containsDigit = true;
                }

                while (temp != 0)
                {
                    int currentDigit = temp % 10;
                    if (currentDigit == digit)
                    {
                        containsDigit = true;
                        break;
                    }

                    temp /= 10;
                }

                if (containsDigit)
                {
                    filteredNumbers.Add(num);
                }
            }

            return filteredNumbers.ToArray();
        }
    }
}
