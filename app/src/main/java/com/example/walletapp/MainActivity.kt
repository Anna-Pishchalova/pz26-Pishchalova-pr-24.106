// MainActivity.kt
package com.example.walletapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.walletapp.ui.theme.WalletAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalletAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WalletApp()
                }
            }
        }
    }
}

@Composable
fun WalletApp() {
    // Текущий выбранный экран
    var selectedScreen by remember { mutableStateOf("Wallet") }

    // Экран кошелька
    if (selectedScreen == "Wallet") {
        WalletScreen(
            onHomeClick = { selectedScreen = "Home" },
            onWalletClick = { selectedScreen = "Wallet" },
            onTrackClick = { selectedScreen = "Track" },
            onProfileClick = { selectedScreen = "Profile" },
            onTopUpClick = { /* Переход на пополнение */ },
            onBankClick = { /* Переход на банк */ },
            onTransferClick = { /* Переход на перевод */ },
            onCardClick = { /* Переход на карту */ },
            onTransactionClick = { transaction ->
                // Переход к деталям транзакции
                println("Выбрана транзакция: ${transaction.description}")
            }
        )
    } else {
        // Заглушки для других экранов
        PlaceholderScreen(
            title = selectedScreen,
            onBackClick = { selectedScreen = "Wallet" }
        )
    }

    // Нижняя навигация (отображается всегда)
    BottomNavigationBar(
        selectedItem = selectedScreen,
        onItemClick = { selectedScreen = it }
    )
}

// ===== Компонент: Заглушка для экранов =====
@Composable
fun PlaceholderScreen(
    title: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "📱 $title",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Экран в разработке",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE)
                )
            ) {
                Text("← Назад в кошелек")
            }
        }
    }
}

// ===== Компонент: Нижняя навигация =====
@Composable
fun BottomNavigationBar(
    selectedItem: String,
    onItemClick: (String) -> Unit
) {
    val items = listOf(
        "Home" to Icons.Default.Home,
        "Wallet" to Icons.Default.AccountBalanceWallet,
        "Track" to Icons.Default.List,
        "Profile" to Icons.Default.Person
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { (label, icon) ->
                val isSelected = selectedItem == label
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onItemClick(label) }
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (isSelected) Color(0xFF6200EE) else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        color = if (isSelected) Color(0xFF6200EE) else Color.Gray,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWalletApp() {
    WalletApp()
}