%{
	int flag = 0;
%}

%%

[<]title[>]xkcd[:][ ]	{flag = 1;}
[<][/]title[>]		{if(flag) fprintf(yyout, "\n"); flag = 0;}
\n			{}
.			{if(flag) ECHO;}

%%

int main(int argc, char * argv[]) {
	int i, lower_limit, upper_limit;
	char *pathname, *str;

	if(argc < 3)
		return -1;
	
	lower_limit = atoi(argv[1]);
	upper_limit = atoi(argv[2]);

	yyout = fopen("xkcdTitles.txt", "w");

	for(i = lower_limit; i <= upper_limit; i++) {
		pathname = (char *) malloc(30);
		str = (char *) malloc(10);

		sprintf(str, "%d", i);
		strcpy(pathname, "./XKCD/");
		strcat(pathname, str);
		strcat(pathname, ".html");
		if(yyin = fopen(pathname, "r")) {
			yylex();
			fclose(yyin);
		}
		else
			printf("%d.html not found!\n", i);

		free(pathname);
		free(str);
	}

	return 0;
}
