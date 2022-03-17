# GYM
Projet dans le cadre du cours génie logiciel.<br />
Le but de ce projet est de créer une application qui gère l'inscription des clients d'un gym à des séances données par des professionnels. L'importance ici n'est pas sur la base de donnée, mais plutôt sur la structure du logiciel et les principes de base du génie logiciel.

# Guide d'utilisation 
Notes générales
    - Toutes les entrées ne doivent pas posséder le symboles ";"<br />
    - Pour confirmer une opération, on doit entrer "Confirmer" exactement, avec
      la majuscule. Dans le cas contraire, l'opération sera annulée<br />
    - S'il est impossible d'annuler un opération, par exemple lors de la création
      d'un client, simplement terminer la création avec de fausses informations
      et ne pas entrer "Confirmer" à la fin <br />
    - Les "0" sont significatif dans les numéros de client/professionnel/service

Section 1 : Agent

    1. Ajouter un service
        - Les codes se service doivent posséder 3 chiffres
        - Le format des dates est JJ-MM-AAAA
        - Le format des heures est HH-MM
        - Les journées doivent être écrites en français et peuvent contenir ou non
          des majuscules
        - La capacité doit être un entier entre 1 et 30
        - Les frais doivent contenir les centimes (dont les "0"), être séparé par une
          virgule et être compris entre "0,01" et "100,00".
        - Les commentaires sont facultatifs et peuvent contenir un maximum de 100
          caractère

    2. Mettre à jour un service
        - Il faut entrer le numéro de service et du professionnel associé pour
          s'assure qu'il peut le modifier
        - Les formats sont les même que pour "Ajouter un service"

    3. Supprimer un service
        - Il faut entrer le numéro de service et du professionnel associé pour
          s'assure qu'il peut le modifier

    4. Ajouter un professionnel
        - Il y a un nombre maximal de 25 caractères pour le nom
        - Il y a un nombre maximal de 25 caractères pour l'addresse
        - Il y a un nombre maximal de 14 caractères pour la ville
        - Il y a un nombre maximal de 2 caractères pour la province

    5. Mettre à jour un professionnel
        - Les formats sont les mêmes que pour "Ajouter un professionnel"

    7. Ajouter un client
        - Les formats sont les mêmes que pour "Ajouter un professionnel"

    8. Mettre à jour un client
        - Les formats sont les mêmes que pour "Ajouter un professionnel"

Section 2 : Client

    1. S'inscrire à une séance
        - On ne peut s'inscrire qu'à une séance se déroulant dans 
          la journée actuelle

    2. Vérifier la validité
        - Utilisez cette option si vous êtes déjà connecté et que
          vous voulez avoir votre code QR

    3. Afficher le répertoire de séance
        - Les séances afficher sont uniquement celles de la journée
          actuelle

Section 3 : Professionnel

    1. Consulter une séance
        - La date entrée doit être sous le format JJ-MM-AAAA et la
          journée doit correspondre à une journée ou le service en
          question a été donné

    2. Confirmer une inscription
        - D'abord entrer le numéro de séance pour accéder à l'option
          de confirmer un client
        - Si le logiciel affiche séance inexistante pour une séance
          de la journée actuelle, cela veut dire que personne ne s'est
          inscrit

    3. Afficher le répertoire de séance
        - Les séances afficher sont uniquement celles de la journée
          actuelle
