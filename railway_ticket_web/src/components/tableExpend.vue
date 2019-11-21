<style scoped>
  .expand-row{
    margin-bottom: 16px;
  }
</style>
<template>
  <div>
    <Row class="expand-row">
      <Col>
        <span class="expand-key">业务信息: </span>
        <span class="expand-value">{{ row.params }}</span>
      </Col><br />
      <Col>
        <span class="expand-key">响应信息: </span>
        <span class="expand-value">{{ row.rtParams }}</span>
      </Col><br />
      <Col>
        <span class="expand-key">浏览器标识: </span>
        <span class="expand-value">{{ row.ua }}</span>
      </Col><br />
      <!--<Col span="8">
        <span class="expand-key">Interest: </span>
        <span class="expand-value">{{ row.rtParams }}</span>
      </Col>
      <Col span="8">
        <span class="expand-key">Birthday: </span>
        <span class="expand-value">{{ row.birthday }}</span>
      </Col>-->
    </Row>
    <Row>
      <Col>
        <span class="expand-key">堆栈信息: </span>
        <span class="expand-value">{{ row.exceptDesc }}</span>
      </Col><br />
      <!--<Col span="8">
        <span class="expand-key">Favorite movie: </span>
        <span class="expand-value">{{ row.movie }}</span>
      </Col>
      <Col span="8">
        <span class="expand-key">Favorite music: </span>
        <span class="expand-value">{{ row.music }}</span>
      </Col>-->
    </Row><br />
    <Row>
      <Col>
        <span class="expand-key">响应描述: </span>
        <span class="expand-value">{{ row.exceptDetail }}</span>
      </Col>
      <!--
      <Col span="8">
        <span class="expand-key">Favorite music: </span>
        <span class="expand-value">{{ row.music }}</span>
      </Col>-->
    </Row>
  </div>
</template>
<script>
    export default {
        props: {
            row: Object
        }
    };
</script>
