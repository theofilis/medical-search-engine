<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<jsp:include page="WEB-INF/jspf/header.jspf" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form-horizontal" action="">
    <div class="control-group">
        <input class="span12" id="query" type="text"
               placeholder="Mycobacterial infections" name="query">
    </div>

    <div class="form-actions" style="padding-left: 10px;">
        <div class="row-fluid">
            <div data-toggle="buttons-radio">
                <div class="row-fluid">
                    <div class="span6">
                        <a id="automatic" href="#myModal" role="button" class="span12 btn" data-toggle="modal">Automatic Query Expansion</a>

                        <!-- Modal -->
                        <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-header">
                                <h3 id="myModalLabel">Select k</h3>
                            </div>
                            <div class="modal-body">
                                <p><input id='k' name='k' type='number' class='input-small' value='5'></p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                            </div>
                        </div>
                    </div>
                    <div class="span6">
                        <div class="dropdown">
                            <button type="button" class="span12 dropdown-toggle btn" data-toggle="dropdown" id="rel">Term Selection</button>
                            <ul id="relatedterm" class="dropdown-menu" role="menu" aria-labelledby="rel">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="control-group">
        <button class="span12 btn btn-success" style="margin-left: 0px;" type="submit" id="button">
            <i class="icon-search icon-white"></i>
        </button>
    </div>
</form>
<div class="row-fluid">
    <div id="resinfo" class="span12">
    </div>
</div>
<div class="row-fluid">
    <div id="main">

    </div>
</div>


<jsp:include page="WEB-INF/jspf/footer.jspf" />       