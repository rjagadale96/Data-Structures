class Solution {
    public boolean isValidSudoku(char[][] board) {
        
        for(int i=0;i<board.length;i++){
            Map<Character,Integer> rowMap = new HashMap();
            Map<Character,Integer> colMap = new HashMap();
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] != '.'){
                    if(rowMap.containsKey(board[i][j])){
                        return false;
                    }else{
                        rowMap.put(board[i][j],1);
                    }
                }
                
            }
            for(int j=0;j<board[0].length;j++){
                if(board[j][i] != '.'){
                    if(colMap.containsKey(board[j][i])){
                        return false;
                    }else{
                        colMap.put(board[j][i],1);
                    }
                }
                
                if(i%3 == 0 && j%3 == 0){
                    if(valid(i,j,board) == false){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private boolean valid(int i, int j, char [][] board){
         Map<Character,Integer> map = new HashMap();
        for(int r=i ; r< i+3 ;r++){
             for(int c=j; c< j+3 ;c++){
                 if(board[r][c] != '.'){
                    if(map.containsKey(board[r][c])){
                         return false;
                     }else{
                         map.put(board[r][c],1);
                     }
                 }
                
             }
        }
        return true;
    }
}