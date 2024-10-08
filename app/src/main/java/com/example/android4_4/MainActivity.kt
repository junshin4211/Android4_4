package com.example.android4_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android4_4.ui.theme.Android4_4Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android4_4Theme {
                Main()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val snackBarHostState = remember { SnackbarHostState()}
    val scope = rememberCoroutineScope()

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary

                ),
                title = { Text(text = "friends") },
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { innerPadding ->

        FriendList(getFriend(),innerPadding) { friend ->
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = "${friend.name}'s phone number is ${friend.phone}",
                    withDismissAction = true
                )
            }
        }
    }
}

@Composable
fun FriendList(
    friends: List<Friend>,
    innerPadding: PaddingValues,
    onItemClick: (Friend) -> Unit
    ) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(friends)
        {
            friend ->
            ListItem(
                modifier = Modifier.clickable {
                    onItemClick(friend)
                },
                headlineContent = { Text(text = friend.name) },
                supportingContent = { Text(text = friend.phone) },
                leadingContent = {
                    Image(
                        painter = painterResource(id = friend.img),
                        contentDescription = "friend image",
                        modifier = Modifier.padding(4.dp)
                    )
                }
            )
            HorizontalDivider()
        }
    }
}

fun getFriend():List<Friend>{
    val friends = listOf(
        Friend("001","IVY","0912343420",R.drawable.baseline_people_24),
        Friend("002","MARY","0900000230",R.drawable.baseline_people_24),
        Friend("003","SUE","0998765432",R.drawable.baseline_people_24),
        Friend("004","TOM","09123456777",R.drawable.baseline_people_24),
        Friend("005","TOM","09123456777",R.drawable.baseline_people_24)
    )
    return friends
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android4_4Theme {
        Main()
    }
}