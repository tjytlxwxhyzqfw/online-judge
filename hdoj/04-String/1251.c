/*
** 我从网页上直接拷贝过来的数据是这样的:每一行以回车换行作为结尾
** 回车是干嘛的?回到此行开头!!!!
** 然后我以为是以换行作为结尾,所以用了word[strlen(word)-1]=0
** 这样做导致,并没有处理word最后的\r
** 于是,老是出现莫名奇妙的错误
** !!!!!!!!!!!!!!!!!
*/

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define WORDMAX 13

typedef struct node node_t;

struct node{
	int count;
	node_t *alph[26];
};

node_t *nullnode;

node_t *
new_node( void ){
	int i;
	node_t *p;
	assert( p=(node_t*)malloc(sizeof(node_t)) );
	p->count = 0;
	for( i=0; i<26; i++ ){
		p->alph[i] = nullnode;
	}
	return p;
}

void
insert( char *word, node_t *tree ){
	node_t *pos = tree;
	while( *word!=0 ){
		if( pos->alph[*word-'a']==nullnode ){
			pos->alph[*word-'a'] = new_node();
		}
		pos->alph[*word-'a']->count += 1;
		pos = pos->alph[*word-'a'];
		word += 1;
	}
}

int
find( char *word, node_t *tree ){
	node_t *pos = tree;
	int times = 0;
	while( *word!= 0 ){
		if( pos->alph[*word-'a']==nullnode ){
			return 0;
		}
		times = pos->alph[*word-'a']->count;
		pos = pos->alph[*word-'a'];
		word += 1;
	}
	return times;
}

int
main( void ){	

	node_t *tree;
	int i, sh=1;
	char word[WORDMAX];

	nullnode = new_node();
	nullnode->count = -1;
	for( i=0; i<26; i++ ){
		nullnode->alph[i] = nullnode;
	}

	tree = new_node();

	freopen( "Inputs/1251", "r", stdin );

	while( 1 ){
		fgets( word, WORDMAX, stdin );
		if( word[strlen(word)-2]=='\r' ){
			/*printf( "yes!\n" );*/
			sh = 2;
		}
		word[strlen(word)-sh] = 0;
		if( strlen(word)==0 ){
			break;
		}
		insert( word, tree );
	}
	while( scanf("%s", word )!=EOF ){
		printf( "%d\n", find(word,tree) );
	}
	return 0;
}
