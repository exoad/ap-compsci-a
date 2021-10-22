# Created and Maintained by Jack Meng
# This file is licensed under the MIT LICENSE with the following:
# Copyright 2021 Jack Meng
# Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#
# To run this file use the following command on a *NIX OS:
# 
# bash seekout.sh
#
# Run at your own risk in accordance with the license (^above)


versinNO="1.0"
welcomeNAME="==Welcome!==\n"
welcomeMSGHEADER="Script Version: ${versinNO}\nAPCS Guide Repository Script\nCreated by Jack Meng (exoad)\nLicensed C-Renewed (C) 2021-2022"
welcomeMSGBODY="\nThis script will help you with accessing much of the APCS repository's content\n"
welcomeMSGBODY1="\nHere are you choices:\n- Type \"1\" to fetch or refresh the content\n- Type \"2\" to present the REAMDE"
welcomeMSGFOOTEr="\n=========\n"


echo -e "\e[31m${welcomeNAME}" ; echo -e "\e[0m" ; echo $welcomeMSGHEADER $welcomeMSGBODY $welcomeMSGBODY1;
echo -e "\e[31m${welcomeMSGFOOTEr}" ; echo -e "\e[0m" ; echo ;

read choiceVAR;

if [ choiceVAR == "1" ] 
  then
   #...
  exit
fi

# WIP
