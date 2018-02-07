package com.ak47.cms.cms.monitor

import com.ak47.cms.cms.dao.ImageRepository
import com.sun.management.OperatingSystemMXBean
import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.endpoint.AbstractEndpoint
import org.springframework.boot.actuate.endpoint.Endpoint
import org.springframework.boot.actuate.endpoint.PublicMetrics
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.boot.actuate.health.Status
import org.springframework.boot.actuate.metrics.Metric
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import java.io.File
import java.lang.management.ManagementFactory
import java.net.InetAddress
import java.util.*


@Component
class ShowEndpoints :
        AbstractEndpoint<MutableMap<String, MyEndpoint>?>("showEndpoints"),
        ApplicationContextAware {

    val log = LoggerFactory.getLogger(ShowEndpoints::class.java)
    private var applicationContext: ApplicationContext? = null

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    override fun invoke(): MutableMap<String, MyEndpoint>? {
        val result: MutableMap<String, MyEndpoint>? = mutableMapOf()

        val endpoints = this.applicationContext?.getBeansOfType(Endpoint::class.java)
        val mvcEndpoints = applicationContext?.getBeansOfType(MvcEndpoint::class.java)
        log.info("endpoints = {}", endpoints)
        log.info("mvcEndpoints = {}", mvcEndpoints)

        endpoints?.forEach {
            val key = it.key
            result?.put(key, MyEndpoint(it.value.id, it.value.isSensitive, it.value.isEnabled))
        }

        mvcEndpoints?.forEach {
            val key = it.key
            result?.put(key, MyEndpoint(it.value.path, it.value.isSensitive, true))
        }

        return result
    }
}

data class MyEndpoint(var id: String, var isSensitive: Boolean, var enabled: Boolean)


@Component
class CustomMetrics
@Autowired
constructor(private val applicationContext: ApplicationContext) : PublicMetrics {
    override fun metrics(): Collection<Metric<*>> {
        val metrics = ArrayList<Metric<*>>()
        metrics.add(Metric<Int>("spring.bean.definitions", applicationContext.beanDefinitionCount))
        metrics.add(Metric<Int>("spring.beans", applicationContext.getBeanNamesForType(Any::class.java).size))
        metrics.add(Metric<Int>("spring.controllers", applicationContext.getBeanNamesForAnnotation(Controller::class.java).size))
        return metrics
    }
}

@Component
class MyCustomHealthIndicator : HealthIndicator {

    override fun health(): Health {
        val errorCode = check() // perform some specific health check
        return if (errorCode != 0) {
            Health.down().withDetail("Error Code", errorCode).build()
        } else Health
                .up()
                .withDetail("imageRepository.selectTest", errorCode)
                .build()
    }

    @Autowired lateinit var imageRepository: ImageRepository
    private fun check(): Int {
        return imageRepository.selectTest()
    }
}


@Component
class ServerEndpoint : Endpoint<Map<String, Map<String, String>>> {
    override fun invoke(): Map<String, Map<String, String>> {
        val serverDetails = mutableMapOf<String, Map<String, String>>()
        try {
            serverDetails["ServerInfo"] = (getServerInfo())
            serverDetails["DiskInfo"] = (getDiskInfo())
            serverDetails["MemInfo"] = (getMemInfo())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return serverDetails
    }

    override fun getId(): String = "serverEndpoint"
    override fun isSensitive(): Boolean = false
    override fun isEnabled(): Boolean = true

    fun getServerInfo(): Map<String, String> {
        val result = mutableMapOf<String, String>()
        result["hostAddress"] = InetAddress.getLocalHost().hostAddress
        result["hostName"] = InetAddress.getLocalHost().hostName
        result["OS"] = System.getProperty("os.name")
        return result
    }

    fun getDiskInfo(): Map<String, String> {
        val result = mutableMapOf<String, String>()
        val disks = File.listRoots()
        for (file in disks) {
            result["freeSpace"] = "${file.freeSpace / 1024 / 1024}M"
            result["usableSpace"] = "${file.usableSpace / 1024 / 1024}M"
            result["totalSpace"] = "${file.totalSpace / 1024 / 1024}M"
        }
        return result
    }

    fun getMemInfo(): Map<String, String> {
        val mem = ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean
        val result = mutableMapOf<String, String>()
        result["totalPhysicalMemorySize"] = "${mem.totalPhysicalMemorySize / 1024 / 1024}M"
        result["freePhysicalMemorySize"] = "${mem.freePhysicalMemorySize / 1024 / 1024}M"
        result["committedVirtualMemorySize"] = "${mem.committedVirtualMemorySize / 1024 / 1024}M"
        result["freeSwapSpaceSize"] = "${mem.freeSwapSpaceSize / 1024 / 1024}M"
        result["totalSwapSpaceSize"] = "${mem.totalSwapSpaceSize / 1024 / 1024}M"
        result["processCpuLoad"] = "${mem.processCpuLoad}"
        result["systemCpuLoad"] = "${mem.systemCpuLoad}"
        result["processCpuTime"] = "${mem.processCpuTime}"
        result["arch"] = mem.arch
        result["availableProcessors"] = "${mem.availableProcessors}"
        result["systemLoadAverage"] = "${mem.systemLoadAverage}"
        result["version"] = mem.version
        return result
    }
}









