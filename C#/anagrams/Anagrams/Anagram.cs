using System;
using System.Collections.Generic;
using System.Globalization;

namespace Anagrams
{
    public class Anagram
    {
        private readonly string sourceWord;

        public Anagram(string? sourceWord)
        {
            if (sourceWord == null)
            {
                throw new ArgumentNullException(nameof(sourceWord));
            }

            if (sourceWord.Length == 0)
            {
                throw new ArgumentException("Source word cannot be empty.", nameof(sourceWord));
            }

            this.sourceWord = sourceWord.ToLower(CultureInfo.CurrentCulture);
        }

        public string[] FindAnagrams(string[]? candidates)
        {
            if (candidates == null)
            {
                throw new ArgumentNullException(nameof(candidates));
            }

            List<string> anagrams = new List<string>();

            foreach (string candidate in candidates)
            {
                if (!this.IsSameWord(candidate) && this.IsAnagram(candidate))
                {
                    anagrams.Add(candidate);
                }
            }

            return anagrams.ToArray();
        }

        private bool IsSameWord(string candidate)
        {
            return candidate.ToLower(CultureInfo.CurrentCulture) == this.sourceWord;
        }

        private bool IsAnagram(string candidate)
        {
            if (candidate.Length != this.sourceWord.Length)
            {
                return false;
            }

            int[] letterCount = new int[26];

            foreach (char c in this.sourceWord)
            {
                letterCount[c - 'a']++;
            }

            foreach (char c in candidate.ToLower(CultureInfo.CurrentCulture))
            {
                int index = c - 'a';
                letterCount[index]--;

                if (letterCount[index] < 0)
                {
                    return false;
                }
            }

            return true;
        }
    }
}
