struct String {
	char opt[6];
    char name[50];
};

program BITACORA_PROG {
  version BITACORA_VERS {
    string ADD(string) = 1;
    string SEARCH(string) = 2;
  } = 1;
} = 0x23451111;
