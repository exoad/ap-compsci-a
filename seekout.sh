# Run this code on a *Nix system to easily be able to grab things from the home repository
# For now this code is only supprts debian, but I will try to implement a support for other distributions like
# homebrew/brew (MacOS), Arch (pamac & pacman), and also snap package.
# To run this on a Windows like OS you must install a Linux Environment (WSL) etc..
# Packages you need installed: vim
# Packages needed but will be auto installed for you are: JDK, GIT
# This script is also implemented in a different way for the view program
#
# This Script is licensed under the MIT License, but all code that use this script or that this script pulls, clones, fetches, pushes, and comes into contact
# with will be licensed under the C-License (Renewed)

mkdir "exoad-apcs-cache" 
cd "exoad-apcs-cache"
wget https://raw.githubusercontent.com/exoad/apcs/view-program/caches/LICENSE_PLAIN.txt
cd ../

errorAPP_NOT_INSTALLED="The program/script is not installed: "
successAPP_INSTALLED=" is installed"
success="Successful."

versinNO="1.0"
welcomeNAME="==Welcome!=="
welcomeMSGFOOTEr="\n============"

repo_FOLDER="/exoad-apcs"

echo -e "\e[31m$welcomeNAME \e[0m" ;
printf "Script Version: v$versinNO\nAPCS Guide Repository Script\nCreated by Jack Meng (exoad)\nLicensed C-Renewed (C) 2021-2022\nThis script will help you with accessing much of the APCS repository's content\n\nHere are you choices:\n- Type \"1\" to fetch or refresh the content\n- Type \"2\" to present the LICENSE"
echo -e "\e[31m$welcomeMSGFOOTEr" ; echo -e "\e[0m" ; echo ;

echo -e "\e[34mEnter your choice: \e[0m" ; 
read choiceVAR;

if [ "$choiceVAR" == "1" ] 
  then
    git --version 2>&1 >/dev/null 
    GIT_IS_AVAILABLE=$?
    if [ "$GIT_IS_AVAILABLE" -eq 0 ]
    then
      echo -e "\e[32mgit$successAPP_INSTALLED" ; echo -e "\e[0m" ; printf "Fetching...\n" ;
      if [ ! -d "$repo_FOLDER" ]
        then
            echo "Cloning the repository into folder $repo_FOLDER" ; echo ;
            git clone --branch master https://github.com/exoad/apcs.git "$repo_FOLDER"
            echo -e "\e[32m $success" ; echo -e "\e[0m" ; echo ;
        exit
      else 
            echo "Repository folder detected, fetching the latest into the folder $repo_FOLDER" ; echo ;
            cd "$repo_FOLDER" 
            git pull
        exit
      fi
    else
        echo -e "\e[31m${errorAPP_NOT_INSTALLED}git" ; exit 1 ;
    fi
elif [ "$choiceVAR" == "2" ] 
  then
    cd exoad-apcs-cache
    vim LICENSE_PLAIN.txt
fi
