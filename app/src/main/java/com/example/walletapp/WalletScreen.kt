// WalletScreen.kt
package com.example.walletapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Модель данных для транзакции
data class Transaction(
    val id: Int,
    val amount: String,
    val description: String,
    val date: String,
    val isIncome: Boolean // true - доход, false - расход
)

@Composable
fun WalletScreen(
    onHomeClick: () -> Unit = {},
    onWalletClick: () -> Unit = {},
    onTrackClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onTopUpClick: () -> Unit = {},
    onBankClick: () -> Unit = {},
    onTransferClick: () -> Unit = {},
    onCardClick: () -> Unit = {},
    onTransactionClick: (Transaction) -> Unit = {}
) {
    // Данные транзакций
    val transactions = listOf(
        Transaction(
            id = 1,
            amount = "-N3,000.00",
            description = "Delivery fee",
            date = "July 7, 2022",
            isIncome = false
        ),
        Transaction(
            id = 2,
            amount = "N2,000.00",
            description = "Lalalalala",
            date = "July 4, 2022",
            isIncome = true
        ),
        Transaction(
            id = 3,
            amount = "-N3,000.00",
            description = "Third Delivery",
            date = "July 1, 2022",
            isIncome = false
        ),
        Transaction(
            id = 4,
            amount = "N1,000.00",
            description = "Another One",
            date = "June 27, 2022",
            isIncome = true
        ),
        Transaction(
            id = 5,
            amount = "N2,500.00",
            description = "Experts Are The Best",
            date = "June 23, 2022",
            isIncome = true
        ),
        Transaction(
            id = 6,
            amount = "-N3,000.00",
            description = "Delivery fee",
            date = "June 17, 2022",
            isIncome = false
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // ===== Верхняя часть с балансом =====
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF6200EE)
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Приветствие
                Text(
                    text = "Hello Ken",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Баланс
                Text(
                    text = "Current balance:",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.6f)
                )

                Text(
                    text = "N10,712:00",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Кнопки "Top Up" и "Bank Transfer"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Кнопка "Top Up"
                    Button(
                        onClick = onTopUpClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF6200EE)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Top Up",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Top Up",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Кнопка "Bank Transfer"
                    Button(
                        onClick = onBankClick,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White.copy(alpha = 0.2f),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Bank Transfer",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Bank",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // ===== Быстрые действия =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            QuickActionItem(
                icon = Icons.Default.ArrowForward,
                label = "Transfer",
                onClick = onTransferClick
            )
            QuickActionItem(
                icon = Icons.Default.CreditCard,
                label = "Card",
                onClick = onCardClick
            )
        }

        // ===== Заголовок "Transaction History" =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Transaction History",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "See All",
                fontSize = 14.sp,
                color = Color(0xFF6200EE),
                modifier = Modifier.clickable { /* Переход к истории */ }
            )
        }

        // ===== Список транзакций =====
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(transactions) { transaction ->
                TransactionItem(
                    transaction = transaction,
                    onClick = { onTransactionClick(transaction) }
                )
            }
        }
    }
}

// ===== Компонент: Быстрое действие =====
@Composable
fun QuickActionItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(
                    color = Color(0xFFE8E0F0),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFF6200EE),
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

// ===== Компонент: Элемент транзакции =====
@Composable
fun TransactionItem(
    transaction: Transaction,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Левая часть: иконка и описание
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Иконка в зависимости от типа транзакции
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(
                            color = if (transaction.isIncome)
                                Color(0xFFE8F5E9)
                            else
                                Color(0xFFFFEBEE)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (transaction.isIncome)
                            Icons.Default.ArrowDownward
                        else
                            Icons.Default.ArrowUpward,
                        contentDescription = null,
                        tint = if (transaction.isIncome)
                            Color(0xFF4CAF50)
                        else
                            Color(0xFFF44336),
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Описание и дата
                Column {
                    Text(
                        text = transaction.description,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(
                        text = transaction.date,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Сумма
            Text(
                text = transaction.amount,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (transaction.isIncome) Color(0xFF4CAF50) else Color(0xFFF44336)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWalletScreen() {
    WalletScreen()
}