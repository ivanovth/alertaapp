package com.example.alertaurbana.Screen.Alerta.Contenido

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertasItem
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Alerta.Contenido.MapaContenido.EmpyPainter
import com.example.alertaurbana.Screen.Alerta.Contenido.MapaContenido.ShowImageDialog
import com.example.alertaurbana.Screen.Alerta.Contenido.ViewModels.MapaScreenViewModel
import com.example.alertaurbana.Screen.Alerta.Contenido.ViewModels.NotificacionesViewmodel
import com.example.alertaurbana.Screen.Login.MyBottomAppBar
import com.example.alertaurbana.Screen.Login.MyTopAppBar
import com.example.alertaurbana.Screen.Textos.ConstantsAlerta
import com.example.alertaurbana.Screen.UtilCompose.VerDia
import com.example.alertaurbana.ui.theme.DIMENS_114dp
import com.example.alertaurbana.ui.theme.DIMENS_12dp
import com.example.alertaurbana.ui.theme.DIMENS_16dp
import com.example.alertaurbana.ui.theme.DIMENS_4dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.absoluteValue

@Composable
fun testScreen() {


    Scaffold(
        topBar = { MyTopAppBar(ConstantsAlerta.TITULOALERTA) },
        bottomBar = { MyBottomAppBar() },
        content = { innerPadding ->


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

//                val images = listOf(
//                    R.drawable.gps1,
//                    R.drawable.gps2,
//                    R.drawable.gps3,
//                    R.drawable.gps1,
//                    R.drawable.gps2,
//                    R.drawable.gps3,
//                )
//                ImageCarousel(imageList = images) { index ->
//                   Log.d("Esto es el index  ", index.toString())
//                }

                // MapboxComposable33()
                //  CenteredScrollingLazyRow()
                //  CenteredScrollingLazyRow()
                //         SliderBanner()
            }


        },

        )


}

@Composable
fun ImageCarousel(imageList: List<Int>, onImageChange: (Int) -> Unit) {
    var selectedIndex by remember { mutableStateOf(0) }
    var rowWidth by remember { mutableStateOf(0) }
    var itemWidth by remember { mutableStateOf(0) }
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .onGloballyPositioned { layoutCoordinates ->
                // Puedes calcular la posición aquí si es necesario
                rowWidth = layoutCoordinates.size.width
            }
            .fillMaxWidth()
    ) {
        itemsIndexed(imageList) { index, image ->
            if (index == selectedIndex) {
                onImageChange(index) // Llamada a la acción al cambiar la imagen
                Log.d("Imagen cambiada", index.toString())
            }
            ImageCard(imageId = image)
        }
    }
}


@Composable
fun ImageCard(imageId: Int) {
    val image: Painter = painterResource(id = imageId)
//    Image(
//        painter = image,
//        contentDescription = "Carousel Image",
//        modifier = Modifier
//            .size(200.dp)
//            .padding(4.dp)
//
//    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(6.dp),
        //elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = image, // Reemplazar 'notification_image' por tu imagen
                contentDescription = "Notification Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Notificación ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Aquí va el detalle de la notificación, .",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

}


@Composable
fun CenteredScrollingLazyRow() {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        state = lazyListState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp) // Ajustar según el ancho deseado para centrar los elementos
    ) {
        items(items.size) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .background(Color.LightGray)
                    .size(width = 200.dp, height = 100.dp)
                    .onGloballyPositioned { layoutCoordinates ->
                        if (index == lazyListState.firstVisibleItemIndex) {
                            // Calcula el desplazamiento para centrar el elemento visible
                            val centerOffset =
                                (lazyListState.layoutInfo.viewportEndOffset - layoutCoordinates.size.width) / 2
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(index, centerOffset)
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(text = items[index], fontSize = 24.sp)
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
//fun CenteredScrollingLazyRow2() {
//    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
//    val listState = rememberLazyListState()
//    val coroutineScope = rememberCoroutineScope()
//    var itemWidth by remember { mutableStateOf(0) } // Width of the centered item
//
//    LazyRow(
//        state = listState,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 100.dp), // Ajuste para el centrado inicial del primer y último elemento
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        itemsIndexed(items) { index, item ->
//            Box(
//                modifier = Modifier
//                    .padding(horizontal = 8.dp, vertical = 16.dp)
//                    .background(Color.LightGray)
//                    .size(width = 200.dp, height = 100.dp)
//                    .onGloballyPositioned { layoutCoordinates ->
//                        // Update width of the item when it gets positioned
//                        if (index == listState.firstVisibleItemIndex) {
//                            itemWidth = layoutCoordinates.size.width
//                        }
//                    },
//                contentAlignment = Alignment.Center
//            ) {
//                Text(text = item, fontSize = 24.sp)
//            }
//        }
//    }
//
//    LaunchedEffect(listState, itemWidth) {
//        snapshotFlow { listState.firstVisibleItemScrollOffset }
//            .collect { scrollOffset ->
//                if (scrollOffset > 0) {
//                    val centerOffset = with(LocalDensity.current) {
//                        ((listState.layoutInfo.viewportEndOffset / 2) - (itemWidth / 2)).toInt()
//                    }
//                    coroutineScope.launch {
//                        listState.scrollToItem(listState.firstVisibleItemIndex, centerOffset)
//                    }
//                }
//            }
//    }
//}
fun SliderBanner(
    elementos: List<DtoAlertasItem>,
    updateLatitud: (latitud: Double) -> Unit,
    updateLongitud: (latitud: Double) -> Unit,
    modifier: Modifier = Modifier
) {
    val isDialogOpen = remember { mutableStateOf(false) }
    val dia = VerDia()

    val items = elementos.filter { it.Fotos != null }
    val pagerState = rememberPagerState(initialPage = 0)

    Log.d("tamaño", items[8].toString())
    LaunchedEffect(pagerState.currentPage) {

        updateLatitud(items[pagerState.currentPage].Fotos[0].latitud)
        updateLongitud(items[pagerState.currentPage].Fotos[0].longitud)

    }

    Column() {
        HorizontalPager(
            count = items.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = DIMENS_16dp),
            modifier = modifier

                .fillMaxWidth()
        ) { page ->
            Card(
                shape = RoundedCornerShape(8.dp),

                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

            ) {
//                Image(
//                    painter = imageSlider[page],
//                    contentDescription = "Image Slider",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize()
//                )
                Column(modifier = Modifier.background(Color.White)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 3.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            items[page].TipoDeAlerta.Nombre.uppercase(Locale.ROOT),
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        )
                    }

//                    Divider(
//                       // color = Color.LightGray, // Color de la línea
//
//                        modifier= Modifier.height(3.dp)// Grosor de la línea
//                    )

                    Row() {
                        Image(
                            painter = // Define your placeholder in drawable
                            rememberAsyncImagePainter(
                                ImageRequest.Builder // Define your error placeholder in drawable
                                    (LocalContext.current)
                                    .data(data = "https://alerta.municusco.com/media/${items[page].Fotos[0].Foto}")
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        placeholder(R.drawable.load) // Define your placeholder in drawable
                                        error(R.drawable.error) // Define your error placeholder in drawable
                                    }).build()
                            ),
                            contentDescription = "Imagen Cargada",
                            modifier = Modifier
                                .size(150.dp)
                                .padding(all = 6.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, Color.White, RoundedCornerShape(4.dp)),

                            contentScale = ContentScale.Crop
                        )
//                        ShowImageDialog(isDialogOpen,     rememberAsyncImagePainter(
//                            ImageRequest.Builder // Define your error placeholder in drawable
//                                (LocalContext.current)
//                                .data(data = "https://alerta.municusco.com/media/${items[page].Fotos[0].Foto}")
//                                .apply(block = fun ImageRequest.Builder.() {
//                                    crossfade(true)
//                                    placeholder(R.drawable.load) // Define your placeholder in drawable
//                                    error(R.drawable.error) // Define your error placeholder in drawable
//                                }).build()
//                        ))
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(90.dp)
                                    .padding(end = 5.dp)
                            ) {
                                Column {
                                    Text(
                                        "Descripción:",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                    Text(
                                        items[page].Descripcion,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        maxLines = 70,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.padding(start = 4.dp),
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                            }

                            Row() {
                                Column {
                                    Text("FECHA:", fontSize = 10.sp, fontWeight = FontWeight.Light)
                                    Text(
                                        dia.convertirFormatoFecha(items[page].FechaAlerta),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                                Divider(modifier = Modifier.width(3.dp))

                                Column {
                                    Text("HORA:", fontSize = 10.sp, fontWeight = FontWeight.Light)
                                    Text(
                                        dia.convertirFormatoHora(items[page].HoraAlerta),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                }

                            }
                        }
                    }


                }


            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,

            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(DIMENS_16dp)
        )
        Log.d("state", pagerState.currentPage.toString())
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderBanner2(
    elementos: List<DtoAlertasItem>,
    sheetElemnto:(index: DtoAlertasItem)-> Unit,
    updateLatitud: (latitud: Double) -> Unit,
    updateLongitud: (latitud: Double) -> Unit,
    modifier: Modifier = Modifier
) {
    var showSheet by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }
    val isDialogOpen = remember { mutableStateOf(false) }
    val dia = VerDia()

    //para mostrar en el dato
    val titulo = remember { mutableStateOf("") }
    val detalle = remember { mutableStateOf("") }
    val imagen = remember { mutableStateOf<Painter>(EmpyPainter()) }
    val fecha = remember { mutableStateOf("") }
    val hora = remember { mutableStateOf("") }


    val items = elementos.filter { it.Fotos != null }
    val pagerState = rememberPagerState(initialPage = 0)

    Log.d("tamaño", items[8].toString())
    LaunchedEffect(pagerState.currentPage) {

        updateLatitud(items[pagerState.currentPage].Fotos[0].latitud)
        updateLongitud(items[pagerState.currentPage].Fotos[0].longitud)
//        titulo.value = items[pagerState.currentPage].TipoDeAlerta.Nombre
//        detalle.value = items[pagerState.currentPage].Descripcion
//            fecha.value = dia.convertirFormatoFecha(items[page].FechaAtencion)
//            hora.value = dia.convertirFormatoHora(items[page].HoraAlerta)

    }
    if (showSheet) {
        BottomSheet(onDismiss = { showSheet = false })

    }
    val imagePainter =
        painterResource(id = R.drawable.urbi_alerta_2) // Asegúrate de tener esta imagen en tus recursos
//    FullScreenDialog(
//        showDialog = showDialog,
//        onCloseDialog = { showDialog = false },
//        title = "Título del Diálogo",
//        detail = "Aquí va el detalle extendido del diálogo.",
//        imagePainter = imagePainter,
//        dateTime = LocalDateTime.now()
//    )


    Column() {
        HorizontalPager(
            count = items.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = DIMENS_4dp),
            modifier = modifier

                .fillMaxWidth()
        ) { page ->

//
//            imagen.value = rememberAsyncImagePainter(
//                ImageRequest.Builder // Define your error placeholder in drawable
//                    (LocalContext.current)
//                    .data(data = "https://alerta.municusco.com/media/${items[page].Fotos[0].Foto}")
//                    .apply(block = fun ImageRequest.Builder.() {
//                        crossfade(true)
//                        placeholder(R.drawable.animation_drawable) // Define your placeholder in drawable
//                        error(R.drawable.error) // Define your error placeholder in drawable
//                    }).build()
//            )


            Surface(
                shape = RoundedCornerShape(16.dp),
                color = CardDefaults.cardColors().containerColor,
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .height(210.dp)
                    .padding(10.dp)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                shadowElevation = 10.dp
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(2f),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {


                        Text(
                            text = items[page].TipoDeAlerta.Nombre.uppercase(Locale.ROOT),
                            lineHeight = 14.sp,
                            fontSize = 12.sp,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 70,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Spacer(modifier = Modifier.height(2.dp))
                        Box(modifier = Modifier.padding(end = 8.dp)) {
                            Column {
                                Text(
                                    fontSize = 12.sp,
                                    text = "Detalle:",
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    lineHeight = 12.sp,
                                    fontSize = 10.sp,
                                    textAlign = TextAlign.Justify,
                                    text = if (items[page].Descripcion.length > 90) {
                                        items[page].Descripcion.take(100) + "  ....."
                                    } else {
                                        items[page].Descripcion
                                    },

                                    )
                            }

                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Hora: ${dia.convertirFormatoHora(items[page].HoraAlerta)}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.titleLarge
                            )

                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Fecha: ${dia.convertirFormatoFecha(items[page].FechaAlerta)}",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.titleLarge
                            )

                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedButton(
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White
                            ),
                            modifier = Modifier.height(25.dp),
                            contentPadding = PaddingValues(3.dp),
                            onClick = {

                                showSheet = true
                                sheetElemnto(items[page])


                            }
                        ) {
                            Text(
                                text = "......",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.ExtraBold,
                                style = LocalTextStyle.current
                            )
                        }
                    }



                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.size(width = 100.dp, height = 140.dp)
                    ) {
                        Image(
                            painter =
                            rememberAsyncImagePainter(
                                ImageRequest.Builder // Define your error placeholder in drawable
                                    (LocalContext.current)
                                    .data(data = "https://alerta.municusco.com/media/${items[page].Fotos[0].Foto}")
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        placeholder(R.drawable.animation_drawable) // Define your placeholder in drawable
                                        error(R.drawable.error) // Define your error placeholder in drawable
                                    }).build()
                            ),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                    }
                }
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,

            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(DIMENS_16dp)
        )
        Log.d("state", pagerState.currentPage.toString())
    }
}


@Composable
fun CardBorder2() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = CardDefaults.cardColors().containerColor,
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .height(210.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {


                Text(
                    text = "Alerta",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))
                Box(modifier = Modifier.padding(end = 8.dp)) {
                    Column {
                        Text(fontSize = 14.sp, text = "Detalle:", fontWeight = FontWeight.SemiBold)
                        Text(
                            lineHeight = 15.sp,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Justify,
                            text = "It is a long established fact that a reader will be distracted by the readable content ...."
                        )
                    }

                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Hora: 12:45pm",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Fecha: 12/12/2066",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleLarge
                    )

                }

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedButton(
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    ),
                    modifier = Modifier.height(25.dp),
                    contentPadding = PaddingValues(3.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "......",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        style = LocalTextStyle.current
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.size(width = 100.dp, height = 140.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.urbi_alerta_2),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
    }
}


@Composable
fun FullScreenDialog(
    showDialog: Boolean,
    onCloseDialog: () -> Unit,
    title: String,
    detail: String,
    imagePainter: Painter,
    dateTime: LocalDateTime
) {
    if (showDialog) {
        Dialog(onDismissRequest = onCloseDialog) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .padding(4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = detail,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = imagePainter,
                        contentDescription = "Detalle",
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Fecha y Hora: ${dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = onCloseDialog,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Cancelar")
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    viewModel: MapaScreenViewModel = hiltViewModel()
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },

        ) {
        contenidoAlerta(viewModel.datosAlerta.TipoDeAlerta.Nombre, viewModel.datosAlerta.Descripcion, viewModel.datosAlerta.Fotos[0].Foto, viewModel.datosAlerta.FechaAlerta, viewModel.datosAlerta.HoraAlerta)
    }
}

@Composable
fun CountryList() {
    val countries = listOf(
        Pair("United States", "\uD83C\uDDFA\uD83C\uDDF8"),
        Pair("Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
        Pair("India", "\uD83C\uDDEE\uD83C\uDDF3"),
        Pair("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
        Pair("France", "\uD83C\uDDEB\uD83C\uDDF7"),
        Pair("Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
        Pair("China", "\uD83C\uDDE8\uD83C\uDDF3"),
        Pair("Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
        Pair("Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
        Pair("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
        Pair("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
    )


    LazyColumn() {
        items(countries) { (country, flag) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = flag,
                    modifier = Modifier.padding(end = 20.dp)
                )
                Text(text = country)
            }
        }
    }
}


@Composable
fun contenidoAlerta(titulo: String, detalle: String, imagen: String, Fecha: String, Hora: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth().height(600.dp)
            .padding(10.dp)
    ) {
        Column() {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(fontSize = 16.sp, fontWeight = FontWeight.Bold, text = titulo)
            }
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .border(0.dp, Color.Transparent, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(6.dp),
            ) {
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(8.dp)) {

                    Text(text = "Detalle", fontWeight = FontWeight.SemiBold)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Text(textAlign = TextAlign.Justify, fontSize = 12.sp, text = detalle)
                    }
                    Row() {
                        Text(text = "Fecha: ${VerDia().convertirFormatoFecha(Fecha)}", fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = "Hora: ${VerDia().convertirFormatoHora(Hora)}", fontWeight = FontWeight.SemiBold)
                    }

                }
            }

            Surface(
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(8.dp)
            ) {
                Image(

                    painter =
                    rememberAsyncImagePainter(
                        ImageRequest.Builder // Define your error placeholder in drawable
                            (LocalContext.current)
                            .data(data = "https://alerta.municusco.com/media/${imagen}")
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                                placeholder(R.drawable.animation_drawable) // Define your placeholder in drawable
                                error(R.drawable.error) // Define your error placeholder in drawable
                            }).build()
                    ),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }


//            Image(
//                painter = painterResource(R.drawable.urbi_alerta_2),
//                contentDescription = "Detalle",
//                modifier = Modifier
//                    .height(200.dp)
//                    .fillMaxWidth(),
//                contentScale = ContentScale.Crop
//            )
//            Text(text = "Fecha ")
//            Text(text = "Hora ")
//            Text("EstaAtendido")
//            Text("Detalle Atencion")
//
//            Text(text = "Fecha Atencion")
//            Text(text = "Hora Atencion")


        }
    }
}