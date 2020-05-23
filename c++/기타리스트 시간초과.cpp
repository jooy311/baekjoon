#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
using namespace std;

int n, m, s;
int result = 0;

void dp(int* ans, vector<vector<int>> memo) {

    for (int i = 1; i < n; i++) {
        vector<int> vv;
        for (int j = 0; j < memo[i - 1].size(); j++) {
            int tmp = memo[i - 1][j] + ans[i];
            int tmp2 = memo[i - 1][j] - ans[i];
            if (tmp <= m && tmp >= 0) {
                vv.push_back(tmp);
                //cout << tmp << " ";
            }
            if (tmp2 <= m && tmp2 >= 0) {
                vv.push_back(tmp2);
                //cout << tmp2 << " ";
            }

        }
        if (vv.size() == 0) {
            result = -1;
            
        }
        memo.push_back(vv);
        vv.clear();
    }
    if (result != -1) {
        for (int i = 0; i < memo.back().size(); i++) {
            vector<int> vs = memo.back();
            if (result < vs[i]) result = vs[i];
        }
    }

    cout << result;
    return;
}

int main() {
    cin >> n >> s >> m;

    int* ans = new int[n];
    memset(ans, 0, sizeof(n));


    vector<vector<int>> memo;

    for (int i = 0; i < n; i++) {
        cin >> ans[i];
    }
    //벡터의 0번째 인덱스에는 처음 볼륨의 +/-값을 셋팅
    vector<int> v;
    if (s - ans[0] <= m && s - ans[0] >= 0) {
        v.push_back(s - ans[0]);
    }
    if (s + ans[0] <= m && s + ans[0] >= 0) {
        v.push_back(s + ans[0]);
    }
    if (v.size() == 0) result = -1;
    memo.push_back(v);
    v.clear();

    dp(ans, memo);

    return 0;
}
