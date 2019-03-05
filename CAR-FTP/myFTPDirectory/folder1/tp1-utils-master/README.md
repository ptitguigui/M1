# Protocol TP1

Dans un nouveau répertoire, reclonez votre dépôt à la date du 25 Février 2019, 23h59.

	git clone VOTRE_REPO
	git checkout `git rev-list -n 1 --before="2019-02-25 23:59" master`

Compilez le projet avec Maven

	mvn package

Executez les tests avec Maven

	mvn test

Executez le serveur avec la configuration root/port

	jar -jar VOTRE_JAR.jar [OPTIONS]
